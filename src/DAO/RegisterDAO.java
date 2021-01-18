package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import BEAN.member;

public class RegisterDAO {
	
	public static boolean registerMember(HttpServletRequest request,Connection conn,member mb) {
		String sql="insert into member(membername,memberpass,fullname,categorymemberid) values(?,?,?,?)";
		
		
		try 
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			String fullname = mb.getFullName();
			String memberpass = mb.getMemberPass();
			String memberName = mb.getMemberName();
			int categorymemberid = 1;
			
			ps.setString(1,memberName);
			ps.setString(2,memberpass );
			ps.setString(3,fullname);
			ps.setInt(4,categorymemberid);
			
			int kq = ps.executeUpdate();
			if(kq!=0) {
				return true;
			}
			ps.close();
		} 
		catch (SQLException e) 
		{
			request.setAttribute("mess",e.getMessage());
		}
		return false;
	}
	
	public static boolean checkMemberName(HttpServletRequest request,Connection conn,String name) {
		String sql="select * from member where membername=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,name);
			ResultSet rs = ps.executeQuery();
			return rs.next();
			}
		 catch (SQLException e) 
		{
			 request.setAttribute("mess",e.getMessage());
		}
		return false;
	}

}
