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

@WebServlet("/Contentgrammarcontroller")
public class Contentgrammarcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Contentgrammarcontroller() {
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
			int id = Integer.parseInt(request.getParameter("id"));
			String content = request.getParameter("content");
			boolean check =  DAO.ListgrammarguidlineDAO.insertGrammarGuidelineContent(conn, id, content);
			if(check == true) {
//				request.setAttribute("notice","Câp nhật thành công");
				RequestDispatcher rd = request.getRequestDispatcher("ListgrammarguidlinemanageForward");
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
