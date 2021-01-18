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

import BEAN.Listgrammarguidline;
@WebServlet("/AddGrammarguidelineController")
public class AddGrammarguidelineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AddGrammarguidelineController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		
		if(request.getCharacterEncoding()==null) {
			request.setCharacterEncoding("UTF-8");
		}
		
		Connection conn = DB.DBConnection.creatConnection();
		String grammarName = request.getParameter("grammarName");
		Listgrammarguidline gr = new Listgrammarguidline();
		gr.setGrammarname(grammarName);
		
		boolean check = DAO.ListgrammarguidlineDAO.insertGrammarguideline(conn, gr);
		
		if(check==true) {
			int idGrammarguideline = DAO.ListgrammarguidlineDAO.retrieveIdGrammarGuideline(conn, gr);
			request.setAttribute("idGrammarguideline",idGrammarguideline);
			RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/InsertGrammarguidelineImage.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("ListgrammarguidlinemanageForward");
			rd.forward(request, response);
		}
		conn.close();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}	
	}

}
