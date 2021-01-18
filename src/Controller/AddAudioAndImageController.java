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
@WebServlet("/AddAudioAndImageController")
public class AddAudioAndImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddAudioAndImageController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DB.DBConnection.creatConnection();
			String check = DAO.ExamDAO.Themaudiohinhanhdethi(conn, request, response);
			if(check.equals("Success")) {
				RequestDispatcher rd = request.getRequestDispatcher("ShowExamList?page=1");
				rd.forward(request, response);
			}
			else {
				request.setAttribute("test",check);
				RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/AddAudioAndImage.jsp");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
