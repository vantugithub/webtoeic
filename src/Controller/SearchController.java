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

import BEAN.Listgrammarguidline;

@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			response.getWriter().append("Served at: ").append(request.getContextPath());
			if(request.getCharacterEncoding()==null) 
			{
				request.setCharacterEncoding("UTF-8");
			}
			response.setContentType("text/html;charset=UTF-8");
			
			
			String name = request.getParameter("name");
			Connection conn =DB.DBConnection.creatConnection();
			List<Listgrammarguidline> list = DAO.SearchDAO.resultSearch(conn, name);
			request.setAttribute("list", list);

			RequestDispatcher rd  = request.getRequestDispatcher("View/ResultSearch.jsp");
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
