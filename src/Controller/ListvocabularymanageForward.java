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

import BEAN.vocabularyguideline;

@WebServlet("/ListvocabularymanageForward")
public class ListvocabularymanageForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListvocabularymanageForward() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DB.DBConnection.creatConnection();
			int count = 4;
			int getRow  = DAO.VocabularyDAO.getRow(conn);
			
			String pageStr = request.getParameter("page");
			int page = Integer.parseInt(pageStr);
			int maxPage = 0;
			if(page==1) {
				
			}else {
				page = page - 1;
				page = page*count + 1;
			}
			
			if((getRow/count)%2==0) {
				maxPage = getRow/count;
			}else {
				maxPage = getRow/count + 1;
			}
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("numberpage", Integer.parseInt(request.getParameter("page")));
			List<vocabularyguideline> list = DAO.VocabularyDAO.list1(conn, page, count);
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/Listvocabularymanage.jsp");
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
