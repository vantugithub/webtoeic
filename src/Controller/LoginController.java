package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.member;
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
		rd.forward(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			if(request.getCharacterEncoding()==null) {
				request.setCharacterEncoding("UTF-8");
			}
			
			
			
			Connection conn = DB.DBConnection.creatConnection();
			String membername = request.getParameter("membername");
			String memberpass= request.getParameter("memberpass");


			member mb = new member();

			mb.setMemberName(membername);
			mb.setMemberPass(memberpass);

			String name = DAO.LoginDAO.returnName(request, conn, mb);
			int memberid = DAO.LoginDAO.getMemberId(conn, mb);

			boolean kt = DAO.LoginDAO.checkUser(request, conn, mb);

			if( kt==true ) 
			{
				int kt1 = DAO.LoginDAO.checkCategory(request, conn, mb);
				if(kt1==1) 
				{
					HttpSession session = request.getSession(true);
					session.setAttribute("sessionuser",name);
					session.setAttribute("sessionuserid", memberid);
					RequestDispatcher rd = request.getRequestDispatcher("UserForward");
					rd.forward(request, response);
				}
				else  {
					if(kt1==2)
					{
						HttpSession session = request.getSession(true);
						session.setAttribute("sessionadmin",name);
						RequestDispatcher rd = request.getRequestDispatcher("AdminForward");
						rd.forward(request, response);
					}
				}
			}
			else 
			{
				request.setAttribute("alert","Mật khẩu sai hoặc tài khoản không có");
				RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
