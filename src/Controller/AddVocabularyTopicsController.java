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

@WebServlet("/AddVocabularyTopicsController")
public class AddVocabularyTopicsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddVocabularyTopicsController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			if(request.getCharacterEncoding()==null) {
				request.setCharacterEncoding("UTF-8");
			}
			
			String vocabularyTopics = request.getParameter("vocabularyname");
			Connection conn = DB.DBConnection.creatConnection();
			boolean check = DAO.VocabularyDAO.insertVocabularyTopics(conn, vocabularyTopics);
			if(check==true) {
				int vocabularyguidelineid = DAO.VocabularyDAO.idVocabularyTopics(conn, vocabularyTopics);
				request.setAttribute("vocabularyguidelineid", vocabularyguidelineid);
				RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/AddImageVocabulary.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd  = request.getRequestDispatcher("ListvocabularymanageForward?page=1");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
