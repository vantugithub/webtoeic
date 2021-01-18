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

@WebServlet("/ShowExamList")
public class ShowExamList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowExamList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn= DB.DBConnection.creatConnection();
	//		List<Examination> ex = DAO.ExamDAO.DisplayExamination(conn);
			int count = 4;
			int sumRow = DAO.ExamDAO.countRow(conn);
			String pageStr = request.getParameter("page");
			int page = Integer.parseInt(pageStr);
			if(page==1) {
			}else {
				page = page -1;
				page = page*count+1;
			}
			int maxPage=0;
			
			if((sumRow/count)%2==0) {
				maxPage = sumRow/count;
			} else {
				maxPage = sumRow/count +1;
			}
			
			List<Examination>listexam = DAO.ExamDAO.pagination(conn, page, count);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("listexam", listexam);
			request.setAttribute("numberpage",Integer.parseInt(request.getParameter("page")));
			conn.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/DisplayExamList.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
