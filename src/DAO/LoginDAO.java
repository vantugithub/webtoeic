package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import BEAN.member;

public class LoginDAO {
	public static boolean checkUser(HttpServletRequest request,Connection conn,member mb) {
		String sql="select * from member where membername = ? AND memberpass = ?";

		try 
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			String name = mb.getMemberName();
			String pass = mb.getMemberPass();

			ps.setString(1,name);
			ps.setString(2,pass);

			ResultSet rs = ps.executeQuery();
			return rs.next();

		} 

		catch (SQLException e) 

		{
			request.setAttribute("mess",e.getMessage());
		}

		return false;
	}

	public static int checkCategory(HttpServletRequest request,Connection conn,member mb) {
		int categoryMember = 0;

		String sql = "select categorymemberid from member where membername='"+mb.getMemberName()+"' and memberpass='"+mb.getMemberPass()+"'";

		try 
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				categoryMember = rs.getInt("categorymemberid");
			}
			ps.close();
			rs.close();

		} catch (SQLException e) 
		{
			request.setAttribute("mess",e.getMessage());
		}

		return categoryMember;
	}
	
	public static String returnName(HttpServletRequest request,Connection conn,member mb) {
		String name="";

		String sql = "select fullname from member where membername='"+mb.getMemberName()+"' and memberpass='"+mb.getMemberPass()+"'";

		try 
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				name = rs.getString("fullname");
			}
			ps.close();
			rs.close();

		} catch (SQLException e)
		{
			request.setAttribute("mess",e.getMessage());
		}
		return name;
	}
	public static int getMemberId(Connection conn,member mb) {
		int memberid = 0;
		String sql = "select memberid from member where membername='"+mb.getMemberName()+"'";
		
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				memberid = rs.getInt("memberid");
			}
			ps.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return memberid;
	}

}
