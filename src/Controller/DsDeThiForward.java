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

import BEAN.Examination;

@WebServlet("/DsDeThiForward")
public class DsDeThiForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DsDeThiForward() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DB.DBConnection.creatConnection();
			String pageStr = request.getParameter("page");
			int page = Integer.parseInt(pageStr);
			int count = 4;
			int maxPage=0;
			if(page==1) {
			}else {
				page = page-1;
				page = page*count +1;
			}
			int row = DAO.DsDeThiDAO.getRow(conn);
			if((row/count)%2==0) {
				maxPage=row/count;
			}else {
				maxPage=row/count +1;
			}
			List<Examination> list=  DAO.DsDeThiDAO.displayDsDeThi(conn, page, count);
			request.setAttribute("listExam",list );
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("numberPage", Integer.parseInt(request.getParameter("page")));
			RequestDispatcher rd = request.getRequestDispatcher("View/DsDethi.jsp");
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
