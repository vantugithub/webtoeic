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

import BEAN.SlideBanner;

@WebServlet("/ControllerForward")
public class ControllerForward extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControllerForward() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());

		Connection conn = DB.DBConnection.creatConnection();
		List<SlideBanner> list = DAO.SlideDAO.displayBanner(conn);
		request.setAttribute("listslidebanner",list);

		RequestDispatcher rd = request.getRequestDispatcher("View/Home.jsp");
		rd.forward(request, response);

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
