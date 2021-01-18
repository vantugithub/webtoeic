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

@WebServlet("/ThemFileAnhReadingController")
public class ThemFileAnhReadingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ThemFileAnhReadingController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			String readexerciseidStr = request.getParameter("readexerciseid");
			int readexerciseid = Integer.parseInt(readexerciseidStr);
			Connection conn = DB.DBConnection.creatConnection();
			String mess = DAO.ReadDAO.uploadSingleFileReading(conn, request, response, readexerciseid);
			
			if(mess.equals("success")) {
				RequestDispatcher rd = request.getRequestDispatcher("HienThiDanhSachDeThiDoc?page=1");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/ThemFileAnhReading.jsp");
				rd.forward(request, response);
			}
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
