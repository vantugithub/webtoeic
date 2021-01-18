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

import BEAN.member;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public RegisterController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			if(request.getCharacterEncoding()==null) {
				request.setCharacterEncoding("UTF-8");
			}
			
			Connection conn = DB.DBConnection.creatConnection();
			String fullname = request.getParameter("fullname");
			String membername = request.getParameter("membername");
			String memberpass = request.getParameter("memberpass");

			member mb = new member();
			mb.setFullName(fullname);
			mb.setMemberName(membername);
			mb.setMemberPass(memberpass);

			boolean checkName = DAO.RegisterDAO.checkMemberName(request, conn,membername);

			if(checkName==false) {
				boolean k = DAO.RegisterDAO.registerMember(request, conn, mb);

				if(k==true) {
					request.setAttribute("message","Register success");
				}
			}
			else {
				request.setAttribute("message"," Username available !!!  Register faild");
			}

			RequestDispatcher rd = request.getRequestDispatcher("View/Register.jsp");
			rd.forward(request, response);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
