package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BEAN.Examination;
import BEAN.Examinationquestion;
import BEAN.result;

public class DsDeThiDAO {
	public static List<Examination> displayDsDeThi(Connection conn,int start,int count){
		List<Examination> ex = new ArrayList<Examination>();

		String sql="select * from examination limit "+(start-1)+", "+count;

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {		
				Examination exx = new Examination();

				exx.setExaminationid(rs.getInt("examinationid"));
				exx.setExaminationame(rs.getString("examinationame"));
				exx.setExaminationage(rs.getString("examinatioimage"));

				ex.add(exx);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}


		return ex;
	}

	public static int getRow(Connection conn) {
		int row  = 0 ;
		String sql="select count(*) from examination";

		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			row= rs.getInt("examinationid");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return row;
	}

	public static List<Examinationquestion> displayQuestion(Connection conn,int examinationid) {
		List<Examinationquestion> ex = new ArrayList<Examinationquestion>();
		String sql="select * from examinationquestion where examinationid="+examinationid;

		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {

				Examinationquestion exx = new Examinationquestion();

				exx.setAudiogg(rs.getString("audiogg"));
				exx.setAudiomp3(rs.getString("audiomp3"));
				exx.setCorrectanswer(rs.getString("correctanswer"));
				exx.setExaminationid(rs.getInt("examinationid"));
				exx.setImagequestion(rs.getString("imagequestion"));
				exx.setNum(rs.getInt("num"));
				exx.setOption1(rs.getString("option1"));
				exx.setOption2(rs.getString("option2"));
				exx.setOption3(rs.getString("option3"));
				exx.setOption4(rs.getString("option4"));
				exx.setParagraph(rs.getString("paragraph"));
				exx.setQuestion(rs.getString("question"));

				ex.add(exx);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return ex;
	}
	public static int numberOfQuestion(Connection conn, int examinationid) {
		int dem=0;
		String sql="select count(*) from examinationquestion where examinationid="+examinationid;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			dem=rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dem;
	}

	public static List<Examinationquestion> getCorrectAnswer(Connection conn, int examinationid){
		List<Examinationquestion> list = new ArrayList<Examinationquestion>();
		String sql="select * from examinationquestion where examinationid="+examinationid;
		try {

			PreparedStatement ps =conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Examinationquestion exx = new Examinationquestion();

				exx.setAudiogg(rs.getString("audiogg"));
				exx.setAudiomp3(rs.getString("audiomp3"));
				exx.setCorrectanswer(rs.getString("correctanswer"));
				exx.setExaminationid(rs.getInt("examinationid"));
				exx.setImagequestion(rs.getString("imagequestion"));
				exx.setNum(rs.getInt("num"));
				exx.setOption1(rs.getString("option1"));
				exx.setOption2(rs.getString("option2"));
				exx.setOption3(rs.getString("option3"));
				exx.setOption4(rs.getString("option4"));
				exx.setParagraph(rs.getString("paragraph"));
				exx.setQuestion(rs.getString("question"));

				list.add(exx);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void luuKetQuaThi(Connection conn,result rss) {
		String sql="insert into result(correctanswernum,incorrectanswernum,time,examinationid,memberid,correctanswerlisten,correctanswerread) values(?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, rss.getCorrectanswernum());
			ps.setInt(2, rss.getIncorrectanswernum());
			ps.setString(3, rss.getTime());
			ps.setInt(4, rss.getExaminationid());
			ps.setInt(5, rss.getMemberid());
			ps.setInt(6, rss.getCorrectanswerlisten());
			ps.setInt(7, rss.getCorrectanswerread());
			
			ps.executeUpdate();
			
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static String dapAn(Connection conn,int examinationid,int i) {
		String dapan="";
		String sql="select correctanswer from examinationquestion where examinationid="+examinationid+" and num="+i;
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				dapan = rs.getString("correctanswer");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dapan;
	}
}
