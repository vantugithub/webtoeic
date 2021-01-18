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

import BEAN.Vocabularycontent;

@WebServlet("/ContentOfVocabularyTopicsController")
public class ContentOfVocabularyTopicsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContentOfVocabularyTopicsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String vocabularyguidelineidStr = request.getParameter("vocabularyguidelineid");
			int vocabularyguidelineid = Integer.parseInt(vocabularyguidelineidStr);
			Connection conn = DB.DBConnection.creatConnection();
			List<Vocabularycontent> list  =  DAO.VocabularyDAO.vocabularyContent(conn, vocabularyguidelineid);
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("View/ContentOfVocabularyTopics.jsp");
			rd.forward(request, response);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
