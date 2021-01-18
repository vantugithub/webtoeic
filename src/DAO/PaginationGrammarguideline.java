package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import BEAN.Listgrammarguidline;
public class PaginationGrammarguideline {
public static List<Listgrammarguidline> paginationGrammarguideline(Connection conn,int start,int count){
	List<Listgrammarguidline> pagin = new ArrayList<Listgrammarguidline>();
	String sql = "select * from grammarguideline limit "+(start -1)+", "+count+"";
	
	try {
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Listgrammarguidline lg =new Listgrammarguidline();
			int id = rs.getInt("grammarguidelineid");
			String grammarname = rs.getString("grammarname");
			String grammarimage = rs.getString("grammarimage");
			
			lg.setGrammarguidlineid(id);
			lg.setGrammarimage(grammarimage);
			lg.setGrammarname(grammarname);
			
			pagin.add(lg);
		}
conn.close();
ps.close();
rs.close();
	} 
	catch (SQLException e) 
	{
		e.printStackTrace();
	}
	
	return  pagin;
}
public static int Countrow(Connection conn)
{
	String sql="select count(*) from grammarguideline";
	int dem = 0;
	try {
		PreparedStatement ps =conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		dem = rs.getInt(1);
	} 
	catch (SQLException e) 
	{
		e.printStackTrace();
	}
	return dem;
}

public static List<Listgrammarguidline> details(Connection conn,int grammarId) {
	List<Listgrammarguidline> lg = new ArrayList<Listgrammarguidline>();
	
	String sql = "select * from grammarguideline where grammarguidelineid='"+grammarId+"'";
	
	try {
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Listgrammarguidline lg1 = new Listgrammarguidline();
			String content =rs.getString("content");
			String grammarname = rs.getString("grammarname");
			String grammarimage = rs.getString("grammarimage");
			int grammarguidlineid = rs.getInt("grammarguidelineid");
			
			lg1.setContent(content);
			lg1.setGrammarimage(grammarimage);
			lg1.setGrammarname(grammarname);
			lg1.setGrammarguidlineid(grammarguidlineid);
			
			lg.add(lg1);
			
		}
	} catch (SQLException e) 
	{
		e.printStackTrace();
	}
	
	return lg;
}

}
