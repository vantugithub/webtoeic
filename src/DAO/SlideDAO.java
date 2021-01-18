package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BEAN.SlideBanner;

public class SlideDAO {
	
	public static List<SlideBanner> displayBanner(Connection conn) {
		List<SlideBanner> list = new ArrayList<SlideBanner>();
		String sql = "select * from slidebanner";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String slideName = rs.getString("slidename");
				String slideContent = rs.getString("slidecontent");
				String slideImage = rs.getString("slideimage");
				
				SlideBanner sb = new SlideBanner();
				sb.setSlideName(slideName);
				sb.setSlideContent(slideContent);
				sb.setSlideImage(slideImage);
				
				list.add(sb);
				
			}
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
		}
		
		
		return list;
		
	}

}
