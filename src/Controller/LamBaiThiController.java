package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.Answeruser;
import BEAN.Examinationquestion;
import BEAN.result;

@WebServlet("/LamBaiThiController")
public class LamBaiThiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LamBaiThiController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			HttpSession session = request.getSession(true);
			Connection conn = DB.DBConnection.creatConnection();
			if(session.getAttribute("sessionuser") != null) {
				
				String sessionuseridStr = request.getParameter("memberid");
				int sessionuserid  = Integer.parseInt(sessionuseridStr);
				String examinationidStr = request.getParameter("examinationid");
				int examinationid = Integer.parseInt(examinationidStr);
				List<Examinationquestion> list = DAO.DsDeThiDAO.displayQuestion(conn, examinationid);
				request.setAttribute("kitutrongdatabase1","\n");
				request.setAttribute("kitutronghtml1","<br/>" );
				request.setAttribute("list", list);
				request.setAttribute("examinationid", examinationid);
				request.setAttribute("memberid",sessionuserid);
				RequestDispatcher rd = request.getRequestDispatcher("View/LamBaiThi.jsp");
				rd.forward(request, response);
			}else {
				request.setAttribute("mess", "Đăng nhập trước khi làm bài");
				RequestDispatcher rd = request.getRequestDispatcher("DsDeThiForward?page=1");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DB.DBConnection.creatConnection();
			String examinationidStr = request.getParameter("examinationid");
			int examinationid = Integer.parseInt(examinationidStr);
			String memberidStr = request.getParameter("memberid");
			int memberid = Integer.parseInt(memberidStr);
			int numberOfQuestion = DAO.DsDeThiDAO.numberOfQuestion(conn, examinationid);
			List<Examinationquestion> listCorrectAnswer = DAO.DsDeThiDAO.getCorrectAnswer(conn, examinationid);
			request.setAttribute("listCorrectAnswer", listCorrectAnswer);
			int dapandung = 0;
			int dapansai = 0;
			int dapanphannghedung = 0 ;
			int dapanphandocdung = 0;
			List<Answeruser> listAnswer = new ArrayList<Answeruser>();
			for(int  i = 1 ; i <= numberOfQuestion ; i++) {
				String ans = request.getParameter("ans["+i+"]");
				String dapan = DAO.DsDeThiDAO.dapAn(conn, examinationid, i);
				if(ans!=null) {
					Answeruser au = new Answeruser();
					au.setNum(i);
					au.setCorrect(ans);
					listAnswer.add(au);
					if(i<4) {
						if(ans.equals(dapan)) {
							dapandung++;
							dapanphannghedung++;
						}
					}
					else {
						if(ans.equals(dapan)) {
							dapandung++;
							dapanphandocdung++;
						}
					}
				} else {
					Answeruser au = new Answeruser();
					au.setNum(i);
					au.setCorrect("Không chọn");
					listAnswer.add(au);
				}
			}
			
			Date data = new Date();
			
			int dapanphannghesai = 4 - dapanphandocdung;
			int dapanphandocsai = DAO.DsDeThiDAO.numberOfQuestion(conn, examinationid) - 4 - dapanphannghedung;
			dapansai = DAO.DsDeThiDAO.numberOfQuestion(conn, examinationid) - dapandung;
			
			result rs =new result();
			
			rs.setCorrectanswernum(dapandung);
			rs.setExaminationid(examinationid);
			rs.setIncorrectanswernum(dapansai);
			rs.setMemberid(memberid);
			rs.setTime(data.toString());
			rs.setCorrectanswerlisten(dapanphannghedung);
			rs.setCorrectanswerread(dapanphandocdung);
			List<result> rss = new ArrayList<result>();
			rss.add(rs);
			request.setAttribute("ketquathi",rss );
			
			request.setAttribute("listAnswer", listAnswer);
			request.setAttribute("dapanphannghesai", dapanphannghesai);
			request.setAttribute("dapanphandocsai", dapanphandocsai);
			request.setAttribute("dapanphandocdung", dapanphandocdung);
			request.setAttribute("dapanphannghedung", dapanphannghedung);
			request.setAttribute("dapandung", dapandung);
			request.setAttribute("data", data.toString());
			request.setAttribute("kitutrongdatabase","\n");
			request.setAttribute("kitutronghtml","<br/>" );
			DAO.DsDeThiDAO.luuKetQuaThi(conn, rs);
			request.setAttribute("examinationid", examinationid);
			request.setAttribute("memberid",memberid);
			RequestDispatcher rd = request.getRequestDispatcher("View/Result.jsp");
			rd.forward(request, response);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
