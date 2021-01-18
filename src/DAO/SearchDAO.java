package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BEAN.Listgrammarguidline;

public class SearchDAO {
public static List<Listgrammarguidline> resultSearch(Connection conn,String name) {
	List<Listgrammarguidline> gr = new ArrayList<Listgrammarguidline>();
	String sql= "select * from grammarguideline where grammarname like'%"+name+"%'";
	try {
		PreparedStatement ps =conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Listgrammarguidline l = new Listgrammarguidline();
			l.setGrammarimage(rs.getString("grammarimage"));
			l.setGrammarguidlineid(rs.getInt("grammarguidelineid"));
			l.setGrammarname(rs.getString("grammarname"));
			gr.add(l);
		}
		conn.close();
		ps.close();
		rs.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return gr;
}
}
