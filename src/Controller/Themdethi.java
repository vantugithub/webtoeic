package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Themdethi")
public class Themdethi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Themdethi() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("examinationid", request.getParameter("examinationid"));
		RequestDispatcher rd = request.getRequestDispatcher("View/Viewadmin/ThemDeThi.jsp");
		rd.forward(request, response);
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
