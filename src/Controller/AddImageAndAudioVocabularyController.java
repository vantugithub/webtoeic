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

@WebServlet("/AddImageAndAudioVocabularyController")
public class AddImageAndAudioVocabularyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddImageAndAudioVocabularyController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/AddAudioAndImageVocabulary.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DB.DBConnection.creatConnection();
			String check = DAO.VocabularyDAO.ThemAudioAndImageVocabulary(conn, request, response);
			if(check.equals("Success")) {
				RequestDispatcher rd =request.getRequestDispatcher("ListvocabularymanageForward?page=1");
				rd.forward(request, response);
			}else {
				request.setAttribute("test", check);
				RequestDispatcher rd =request.getRequestDispatcher("View/Viewadmin/AddAudioAndImageVocabulary.jsp");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
