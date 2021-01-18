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

@WebServlet("/AddListeningController")
public class AddListeningController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddListeningController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			String listenexercisename = request.getParameter("listenexercisename");
			Connection conn = DB.DBConnection.creatConnection();
			boolean check = DAO.ListeningDAO.insertListening(conn, listenexercisename);
			if(check==true) {
				int listenexerciseid = DAO.ListeningDAO.getListenexerciseid(conn, listenexercisename);
				request.setAttribute("listenexerciseid", listenexerciseid);
				RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/AddFileImageListening.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("HienThiDanhSachDeThiNghe?page=1");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
