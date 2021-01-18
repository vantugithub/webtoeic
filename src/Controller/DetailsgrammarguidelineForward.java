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

import BEAN.Cmt;
import BEAN.Listgrammarguidline;
@WebServlet("/DetailsgrammarguidelineForward")
public class DetailsgrammarguidelineForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DetailsgrammarguidelineForward() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("utf-8");
			Connection conn = DB.DBConnection.creatConnection();
			response.getWriter().append("Served at: ").append(request.getContextPath());
			String id= request.getParameter("grammarguidlineid");
			int grammarId = Integer.parseInt(id);
			request.setAttribute("grammarId",grammarId );
			
			List<Cmt> list1 = DAO.CmtDAO.displayCmt(conn, grammarId);
			request.setAttribute("listcommentgrammar", list1);
			
			List<Listgrammarguidline> list = DAO.PaginationGrammarguideline.details(conn, grammarId);
			request.setAttribute("list",list);
			request.setAttribute("kitutrongdatabase1","\n");
			request.setAttribute("kitutronghtml1","<br/>" );
			request.setAttribute("grammarid",grammarId );
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Detailsgrammarguideline.jsp");
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
