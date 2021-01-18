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

@WebServlet("/Uploadgrammarguidelineimage")
public class Uploadgrammarguidelineimage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Uploadgrammarguidelineimage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DB.DBConnection.creatConnection();
			int idGrammarguideline = Integer.parseInt(request.getParameter("idGrammarguideline"));
			String check = DAO.ListgrammarguidlineDAO.uploadSingleFile(conn,request, response,idGrammarguideline);
			if(check.equals("success")) {
				RequestDispatcher rd = request.getRequestDispatcher("ListgrammarguidlinemanageForward");
				rd.forward(request, response);
			}
			else {
				request.setAttribute("mess",check);
				request.setAttribute("idGrammarguideline",idGrammarguideline);
				RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/InsertGrammarguidelineImage.jsp");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
