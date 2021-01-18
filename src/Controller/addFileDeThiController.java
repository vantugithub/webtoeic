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

@WebServlet("/addFileDeThiController")
public class addFileDeThiController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public addFileDeThiController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id  = request.getParameter("examinationid");
			Connection conn = DB.DBConnection.creatConnection();
			int examinationid  = Integer.parseInt(id);

			String check = DAO.ExamDAO.uploadSingleFile1(conn, request, response, examinationid);
			if(check.equals("success")) {
				DAO.ExamDAO.checked(conn, examinationid);
				RequestDispatcher rd = request.getRequestDispatcher("ShowExamList?page=1");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("ShowExamList?page=1");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
