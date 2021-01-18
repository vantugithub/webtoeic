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

@WebServlet("/ThemTenDeThiReadingController")
public class ThemTenDeThiReadingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ThemTenDeThiReadingController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			Connection conn = DB.DBConnection.creatConnection();
			String readname = request.getParameter("readname");
			boolean check = DAO.ReadDAO.insertReading(conn, readname);
			if(check==true) {
				int readexerciseid  = DAO.ReadDAO.getReadexerciseid(conn, readname);
				request.setAttribute("readexerciseid", readexerciseid);
				RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/ThemFileAnhReading.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("HienThiDanhSachDeThiDoc?page=1");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
