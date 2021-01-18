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

@WebServlet("/deleteController")
public class deleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public deleteController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		try {
			Connection conn = DB.DBConnection.creatConnection();
			String grammarguidelineid = request.getParameter("grammarguidelineid");
			int grammarguidelineid1 = Integer.parseInt(grammarguidelineid);
//			String numberpage = request.getParameter("numberpage");
//			int numberpage1 = Integer.parseInt(numberpage);

			DAO.ListgrammarguidlineDAO.deleteCmtInGrammar(conn, grammarguidelineid1);
			DAO.ListgrammarguidlineDAO.deleteGrammar(conn, grammarguidelineid1);

			RequestDispatcher rd =request.getRequestDispatcher("ListgrammarguidlinemanageForward?page=1");
			rd.forward(request, response);
			conn.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
