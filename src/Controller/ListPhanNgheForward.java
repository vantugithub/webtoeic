package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Listening;

@WebServlet("/ListPhanNgheForward")
public class ListPhanNgheForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListPhanNgheForward() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Connection conn= DB.DBConnection.creatConnection();
		String pageStr  = request.getParameter("page");
		int page = Integer.parseInt(pageStr);
		int getRow = DAO.ListeningDAO.getRow(conn);
		int count  = 4;
		int maxPage=0;
		if(page==1) {
		}else {
			page --;
			page = page*count + 1;
		}
		if((getRow/count)%2==0) {
			maxPage = getRow/count;
		}else{
			maxPage = getRow/count;
		}
		
		request.setAttribute("maxPage", maxPage);
		List<Listening> list = DAO.ListeningDAO.list(conn, page, count);
		request.setAttribute("list", list);
		request.setAttribute("numberPage", Integer.parseInt(request.getParameter("page")));
		RequestDispatcher rd = request.getRequestDispatcher("View/DSPhanNghe.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
