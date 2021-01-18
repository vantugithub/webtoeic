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

@WebServlet("/AddExamController")
public class AddExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddExamController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getCharacterEncoding()==null) {
				request.setCharacterEncoding("UTF-8");
			}
			Connection conn = DB.DBConnection.creatConnection();
			String examinationame = request.getParameter("examinationame");
			boolean check = DAO.ExamDAO.addExamName(conn, examinationame);
			if(check==true) {
				int examinationid = DAO.ExamDAO.getExamId(conn, examinationame);
				
				request.setAttribute("examinationid",examinationid);
				RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/InsertExamImage.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("ShowExamList");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
