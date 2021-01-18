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

import BEAN.Cmt;


@WebServlet("/CmtController")
public class CmtController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CmtController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getCharacterEncoding()!= null)
		{
			request.setCharacterEncoding("UTF-8");
		}
		
		try {
			Connection conn = DB.DBConnection.creatConnection();
			String memberid = request.getParameter("memberid");
			int memberid1 = Integer.parseInt(memberid);
			String grammarid = request.getParameter("grammarid");
			int grammarid1 = Integer.parseInt(grammarid);
			String content = request.getParameter("content");
			Cmt cmt = new Cmt();
			cmt.setMemberId(memberid1);
			cmt.setGrammarGuidelineId(grammarid1);
			cmt.setCmtGrammarContent(content);
			DAO.CmtDAO.insertCmt(conn, cmt);
			
			List<Cmt> list = DAO.CmtDAO.displayCmt(conn, grammarid1);
			request.setAttribute("listcommentgrammar", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Listcmtgrammarguide.jsp");
			rd.forward(request, response);
			conn.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

}
