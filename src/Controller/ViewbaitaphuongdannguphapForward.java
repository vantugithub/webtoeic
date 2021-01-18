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

@WebServlet("/ViewbaitaphuongdannguphapForward")
public class ViewbaitaphuongdannguphapForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewbaitaphuongdannguphapForward() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("utf-8");		
			int count  = 3;
			String pageidstr = request.getParameter("pageid");
			int pageid = Integer.parseInt(pageidstr);
			Connection conn = DB.DBConnection.creatConnection();
			if(pageid == 1 ) {

			}
			else {
				pageid = pageid - 1;
				pageid = pageid * count + 1;
			}
			int sumRow = DAO.PaginationGrammarguideline.Countrow(conn);
			List<Listgrammarguidline> list = DAO.PaginationGrammarguideline.paginationGrammarguideline(conn, pageid, count);
			int maxPageId;
			if((sumRow/count)%2==0) {
				maxPageId = (sumRow/count);
			}
			else {
				maxPageId = (sumRow/count)+1;
			}
			request.setAttribute("sum",sumRow);
			request.setAttribute("maxPageId", maxPageId);
			request.setAttribute("list", list);
			request.setAttribute("numberPage",Integer.parseInt(pageidstr));
			
			RequestDispatcher rd= request.getRequestDispatcher("View/viewbaitaphuongdannguphap.jsp");
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
