package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BEAN.Cmt;

public class CmtDAO {
public static void insertCmt(Connection conn,Cmt cmt){
	
	String sql = "insert into cmtgrammar(cmtgrammarcontent,memberid,grammarguidelineid) values(?,?,?)";
	
	try {
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,cmt.getCmtGrammarContent());
		ps.setInt(2, cmt.getMemberId());
		ps.setInt(3, cmt.getGrammarGuidelineId());
		
		ps.executeUpdate();
		
	} 
	catch (SQLException e) 
	{
		e.printStackTrace();
	}
}

public static List<Cmt> displayCmt(Connection conn,int grammarId) {
	List<Cmt> cmt = new ArrayList<Cmt>();
	
	String sql="select * from cmtgrammar where grammarguidelineid='"+grammarId+"'";
	
	try {
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Cmt cmt1 = new Cmt();
			
			cmt1.setCmtGrammarContent(rs.getString("cmtgrammarcontent"));
			int memberid = rs.getInt("memberid");
			String name = DAO.CmtDAO.getName(conn, memberid);
			cmt1.setName(name);
			
			cmt.add(cmt1);
		}
	} 
	catch (SQLException e) 
	{
		e.printStackTrace();
	}
	return cmt;
}

public static String getName(Connection conn,int memberid) {
	String name="";
	String sql = "select fullname from member where memberid='"+memberid+"'";
	try {
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			name = rs.getString("fullname");
		}
		
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	return name;
}

}
