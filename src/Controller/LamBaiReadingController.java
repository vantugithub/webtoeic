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

import BEAN.ReadingQuestion;


@WebServlet("/LamBaiReadingController")
public class LamBaiReadingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LamBaiReadingController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			Connection conn = DB.DBConnection.creatConnection();
			HttpSession session  = request.getSession(true);
			if(session.getAttribute("sessionuser") != null) {
				
				String pageStr  = request.getParameter("page");
				int page = Integer.parseInt(pageStr);
				
				String readexerciseidStr  = request.getParameter("readexerciseid");
				int readexerciseid = Integer.parseInt(readexerciseidStr);
				
				int getRow  = DAO.ReadDAO.getRowQuestion(conn, readexerciseid);
				int maxPage=0;
				int count = 1;
				if(page==1) {
					
				}else {
					page--;
					page=page*count +1;
				}
				if((getRow/count)%2==0) maxPage=getRow/count;
				else maxPage = getRow/count + 1 ;
				
				
				List<ReadingQuestion>list  = DAO.ReadDAO.listQ(conn, page, count, readexerciseid);
				request.setAttribute("list",list);
				
				request.setAttribute("maxPage", maxPage);
				request.setAttribute("numberPage", Integer.parseInt(request.getParameter("page")));
				request.setAttribute("readexerciseid", readexerciseid);
				
				RequestDispatcher rd = request.getRequestDispatcher("View/LamBaiTapReading.jsp");
				rd.forward(request, response);
			}else {
				
				request.setAttribute("mess", "Đăng nhập trước khi làm bài");
				RequestDispatcher rd = request.getRequestDispatcher("ListPhanDocForward?page=1");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			String kq = request.getParameter("kq");
			Connection conn = DB.DBConnection.creatConnection();
			if(kq=="") {
				request.setAttribute("msglambtphandoc","Yêu cầu trả lời hết các câu hỏi");
				
				RequestDispatcher rd = request.getRequestDispatcher("View/ThongBaoLoiBaiTapDoc.jsp");
				rd.forward(request, response);
			}else {
				String numStr = request.getParameter("num");
				int num  = Integer.parseInt(numStr);
				
				String readexerciseidStr  = request.getParameter("readexerciseid");
				int readexerciseid = Integer.parseInt(readexerciseidStr);
				
				List<ReadingQuestion> list  = DAO.ReadDAO.listDapAn(conn, readexerciseid,num);
				request.setAttribute("list", list);
				
				request.setAttribute("kq", kq);
				
				RequestDispatcher rd = request.getRequestDispatcher("View/KetQuaBaiTapDoc.jsp");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
