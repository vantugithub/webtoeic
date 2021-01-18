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

@WebServlet("/addFileVocabularyController")
public class addFileVocabularyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public addFileVocabularyController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DB.DBConnection.creatConnection();
			String vocabularyguidelineidStr = request.getParameter("vocabularyguidelineid");
			int vocabularyguidelineid = Integer.parseInt(vocabularyguidelineidStr);
			String check  = DAO.VocabularyDAO.uploadSingleFile(conn, request, response, vocabularyguidelineid);
			if(check.equals("success")) {
				RequestDispatcher rd = request.getRequestDispatcher("ListvocabularymanageForward?page=1");
				rd.forward(request, response);
			}else {
				request.setAttribute("mess", check);
				RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/AddFileExcelVocabulary.jsp");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
