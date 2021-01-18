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

@WebServlet("/ListgrammarguidlinemanageForward")
public class ListgrammarguidlinemanageForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListgrammarguidlinemanageForward() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		try {
			
			Connection conn = DB.DBConnection.creatConnection();
//			List<Listgrammarguidline> list = DAO.ListgrammarguidlineDAO.displayListgrammarline(request, conn);
//			request.setAttribute("listgrammarguidline", list);
			String pageStr = request.getParameter("page");
			int page  = Integer.parseInt(pageStr);
			int count = 4;
			
			if(page==1) {
				
			} else {
				page--;
				page = page*count+1;
			}
			List<Listgrammarguidline> list = DAO.ListgrammarguidlineDAO.phantrang(conn, page, count);
			int maxRow = DAO.ListgrammarguidlineDAO.cout(conn);
			int maxPage = 0;
			if((maxRow/count)%2==0) {
				maxPage = maxRow/count;
			}else {
				maxPage = maxRow/count +1;
			}
			
			request.setAttribute("list", list);
			request.setAttribute("maxPage",maxPage);
			request.setAttribute("numberpage", Integer.parseInt(pageStr));
			request.setAttribute("listgrammarguidline", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/Listgrammarguidlinemanage.jsp");
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
