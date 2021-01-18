package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.ListeningQuestion;

@WebServlet("/LamBaiTapNgheController")
public class LamBaiTapNgheController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LamBaiTapNgheController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		try {
			Connection conn = DB.DBConnection.creatConnection();
			HttpSession session  = request.getSession(true);
			if(session.getAttribute("sessionuser") != null) {
				String listenexerciseidStr = request.getParameter("listenexerciseid");
				int listenexerciseid = Integer.parseInt(listenexerciseidStr);
				int count =1;
				int getRow1 = DAO.ListeningDAO.getRow1(conn,listenexerciseid);
				String pageStr = request.getParameter("page");
				int page = Integer.parseInt(pageStr);
				int maxPage = 0;
				if(page==1) {
					
				}else {
					page--;
					page=page*count+1;
				}
				if((getRow1/count)%2==0) {
					maxPage=getRow1/count;
				}else {
					maxPage=getRow1/count  +1;
				}
				
				List<ListeningQuestion> danhsachcauhoibtnghe  = DAO.ListeningDAO.listListeningQuestion(conn, listenexerciseid, page, count);
				request.setAttribute("listenexerciseid", listenexerciseid);
				request.setAttribute("danhsachcauhoibtnghe", danhsachcauhoibtnghe);
				request.setAttribute("maxPage", maxPage);
				request.setAttribute("numberPage", Integer.parseInt(request.getParameter("page")));
				RequestDispatcher rd = request.getRequestDispatcher("View/LamBaiTapListening.jsp");
				rd.forward(request, response);
			}
			else {
				request.setAttribute("mess", "Đăng nhập trước khi làm bài");
				RequestDispatcher rd  =request.getRequestDispatcher("ListPhanNgheForward?page=1");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String kq = request.getParameter("kq");
		Connection conn = DB.DBConnection.creatConnection();
		if(kq=="") {
			request.setAttribute("msglambtphannghe","Yêu cầu trả lời hết các câu hỏi");
			RequestDispatcher rd = request.getRequestDispatcher("View/ThongBaoLoiBaiTapNghe.jsp");
			rd.forward(request, response);
		}else {
			String numStr = request.getParameter("num");
			int num  = Integer.parseInt(numStr);
			
			String readexerciseidStr  = request.getParameter("listenexerciseid");
			int listenexerciseid = Integer.parseInt(readexerciseidStr);
			
			List<ListeningQuestion> list  = DAO.ListeningDAO.listDapAn(conn, listenexerciseid,num);
			
			request.setAttribute("dapandungbtnghe", list);
			
			request.setAttribute("kq", kq);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/KetQuaBaiTapNghe.jsp");
			rd.forward(request, response);
		}
	}

}
