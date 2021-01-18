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

@WebServlet("/Contentforward")
public class Contentforward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Contentforward() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Connection conn = DB.DBConnection.creatConnection();
		String content = DAO.ListgrammarguidlineDAO.displayContent(conn, id);
		request.setAttribute("content",content);
		request.setAttribute("id",id);
		RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/Insertgrammarguidlinecontent.jsp");
		rd.forward(request, response);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
