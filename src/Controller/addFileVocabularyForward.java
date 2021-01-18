package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addFileVocabularyForward")
public class addFileVocabularyForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public addFileVocabularyForward() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String vocabularyguidelineidStr = request.getParameter("vocabularyguidelineid");
		int vocabularyguidelineid  = Integer.parseInt(vocabularyguidelineidStr);
		request.setAttribute("vocabularyguidelineid", vocabularyguidelineid);
		RequestDispatcher rd  =request.getRequestDispatcher("View/Viewadmin/AddFileExcelVocabulary.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
