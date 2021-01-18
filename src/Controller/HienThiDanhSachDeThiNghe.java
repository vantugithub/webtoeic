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

@WebServlet("/HienThiDanhSachDeThiNghe")
public class HienThiDanhSachDeThiNghe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HienThiDanhSachDeThiNghe() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Connection conn =DB.DBConnection.creatConnection();
		String pageStr = request.getParameter("page");
		int page = Integer.parseInt(pageStr);
		
		int count = 3; 
		if(page==1) {
			
		}
		else {
			page--;
			page=page*count + 1;
		}
		int maxPage=0;
		int getRow = DAO.ListeningDAO.getRow(conn);
		if((getRow/count)%2==0) {
			maxPage = getRow/count;
		}else {
			maxPage = getRow/count +1;
		}
		
		List<Listening> list = DAO.ListeningDAO.listListening(conn, page, count);
		request.setAttribute("listListening", list);
		request.setAttribute("maxPage",maxPage);
		request.setAttribute("numberpage", Integer.parseInt(request.getParameter("page")));
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/ListDanhSachDeThiNgheAdmin.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
