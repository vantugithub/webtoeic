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

@WebServlet("/addImageExamController")
public class addImageExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public addImageExamController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String examinationid = request.getParameter("examinationid");
			int examinationid1 = Integer.parseInt(examinationid);
			
			Connection conn = DB.DBConnection.creatConnection();
			DAO.ExamDAO.uploadSingleFile(conn, request, response, examinationid1);
			RequestDispatcher rd = request.getRequestDispatcher("ShowExamList?page=1");
			rd.forward(request, response);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
