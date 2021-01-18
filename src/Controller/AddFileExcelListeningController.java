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

@WebServlet("/AddFileExcelListeningController")
public class AddFileExcelListeningController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddFileExcelListeningController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String listenexerciseidStr = request.getParameter("listenexerciseid");
		int listenexerciseid = Integer.parseInt(listenexerciseidStr);
		request.setAttribute("listenexerciseid", listenexerciseid);
		RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/AddFileExcelListening.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String listenexerciseidStr = request.getParameter("listenexerciseid");
			int listenexerciseid  = Integer.parseInt(listenexerciseidStr);
			Connection conn = DB.DBConnection.creatConnection();
			String check = DAO.ListeningDAO.uploadSingleFile1(conn, request, response, listenexerciseid);
			if(check.equals("success")) {
				RequestDispatcher rd = request.getRequestDispatcher("HienThiDanhSachDeThiNghe?page=1");
				rd.forward(request, response);
			}else {
				request.setAttribute("mess", check);
				RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/AddFileExcelListening.jsp");
				rd.forward(request, response);
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
