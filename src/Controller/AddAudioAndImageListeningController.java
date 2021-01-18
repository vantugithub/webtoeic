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

@WebServlet("/AddAudioAndImageListeningController")
public class AddAudioAndImageListeningController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddAudioAndImageListeningController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/AddAudioAndImageListening.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Connection conn = DB.DBConnection.creatConnection();
		String check = DAO.ListeningDAO.uploadSingleFileAudioAndImage(conn, request, response);
		if(check.equals("success")) {
			RequestDispatcher rd = request.getRequestDispatcher("HienThiDanhSachDeThiNghe?page=1");
			rd.forward(request, response);
		}else {
			request.setAttribute("mess", check);
			RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/AddAudioAndImageListening.jsp");
			rd.forward(request, response);
		}
		try {
			conn.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
