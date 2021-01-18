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

@WebServlet("/AddFileExcelReadingController")
public class AddFileExcelReadingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddFileExcelReadingController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String readexerciseidStr  = request.getParameter("readexerciseid");
		int readexerciseid  = Integer.parseInt(readexerciseidStr);
		request.setAttribute("readexerciseid", readexerciseid);
		RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/AddFileExcelReadingView.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String readexerciseidStr  = request.getParameter("readexerciseid");
			int readexerciseid  = Integer.parseInt(readexerciseidStr);
			Connection conn = DB.DBConnection.creatConnection();
			String check = DAO.ReadDAO.uploadSingleFileReadingExcel(conn, request, response, readexerciseid);
			if(check.equals("success")) {
				RequestDispatcher rd = request.getRequestDispatcher("HienThiDanhSachDeThiDoc?page=1");
				rd.forward(request, response);
			}else {
				request.setAttribute("mess", check);
				RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/AddFileExcelReadingView.jsp");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
