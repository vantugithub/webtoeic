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

@WebServlet("/AddImageListeningController")
public class AddImageListeningController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddImageListeningController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		try {
			Connection conn = DB.DBConnection.creatConnection();
			String listenexerciseidStr = request.getParameter("listenexerciseid");
			int listenexerciseid  = Integer.parseInt(listenexerciseidStr);
			String mess = DAO.ListeningDAO.uploadSingleFile(conn, request, response, listenexerciseid);
			if(mess.equals("success")) {
				RequestDispatcher rd = request.getRequestDispatcher("HienThiDanhSachDeThiNghe?page=1");
				rd.forward(request, response);
			}else {
				request.setAttribute("mess", mess);
				RequestDispatcher rd  = request.getRequestDispatcher("View/Viewadmin/AddFileImageListening.jsp");
				rd.forward(request, response);
			}
			conn.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
