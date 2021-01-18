package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import BEAN.Listgrammarguidline;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



public class ListgrammarguidlineDAO {

	public static List<Listgrammarguidline> displayListgrammarline(HttpServletRequest request,Connection conn) {
		List<Listgrammarguidline> list = new ArrayList<Listgrammarguidline>();
		String sql="select * from grammarguideline ";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				int grammarguidlineid = rs.getInt("grammarguidelineid");
				String grammarname = rs.getString("grammarname");
				String grammarimage = rs.getString("grammarimage");
				String content = rs.getString("content");

				Listgrammarguidline lg = new Listgrammarguidline();
				lg.setGrammarguidlineid(grammarguidlineid);
				lg.setGrammarname(grammarname);
				lg.setGrammarimage(grammarimage);
				lg.setContent(content);

				list.add(lg);

			}
			ps.close();
			conn.close();
			rs.close();

		} catch (SQLException e) {
			request.setAttribute("mess", e.getMessage());
		}

		return list;
	}

	public static boolean insertGrammarguideline(Connection conn,Listgrammarguidline gr) {
		String sql="insert into grammarguideline(grammarname) values(?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			String grammarName = gr.getGrammarname();
			ps.setString(1,grammarName);
			int kq = ps.executeUpdate();
			if(kq != 0) {
				return true;
			}
			ps.close();
			conn.close();

		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	public static String uploadSingleFile(Connection conn,HttpServletRequest request,HttpServletResponse response,int idGrammarguideline) 
			throws ServletException, IOException {
		String mess="";
		ServletContext context = request.getServletContext();
		final String address = context.getRealPath("/UploadImage");
		//		PrintWriter out = response.getWriter();

		final int yourMaxMemorySize = 1024 * 1024 * 3; 
		final int yourMaxRequestSize = 1024 * 1024 * 50;

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart)
		{
			mess="not have entypecrip: multipart/form-data";
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(yourMaxMemorySize);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		// Create a new file upload handler

		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setSizeMax(yourMaxRequestSize);
		try {
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();

			while (iter.hasNext()) {
				FileItem item = iter.next();

				if (!item.isFormField()) {
					String fileName  = item.getName();
					//vi tri file ma ban muon gui vao server
					String pathFile  = address + File.separator +  fileName;
					File uploadedFile = new File(pathFile);
					boolean kt = uploadedFile.exists();
					try 
					{
						if(kt==true) {
							mess="File đã tồn tại";
						}
						else 
						{
							item.write(uploadedFile);
							insertLinkOfImageFile(conn,fileName, idGrammarguideline);
							mess="success";
							conn.close();
						}
					} 
					catch (Exception e) 
					{
						request.setAttribute("mess",e.getMessage());
					}
				}
				else 
				{
					mess="Upload file thất bại";
				}
			}

		} 
		catch (FileUploadException e) 
		{
			request.setAttribute("mess",e.getMessage());
		}
		return mess;
	}

	public static int retrieveIdGrammarGuideline(Connection conn,Listgrammarguidline gr) {
		int x = 0;
		String sql = "select grammarguidelineid from grammarguideline where grammarname='"+gr.getGrammarname()+"'";


		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				x = rs.getInt("grammarguidelineid");
			}
			ps.close();
			conn.close();
			rs.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return x;
	}

	//insert link of image file

	public static void insertLinkOfImageFile(Connection conn,String image,int idGrammarguideline) {
		String sql= "update grammarguideline set grammarimage=? where grammarguidelineid=? ";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,image);
			ps.setInt(2,idGrammarguideline);
			ps.executeUpdate();

			conn.close();
			ps.close();

		} catch (SQLException e) 
		{
			e.printStackTrace();
		}

	}
	public static boolean insertGrammarGuidelineContent(Connection conn,int grammarid,String content) {

		String sql = "update grammarguideline set content=? where grammarguidelineid='"+grammarid+"'";
		try 
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,content);
			if(ps.executeUpdate() != 0) {
				return true;
			}
			ps.close();
			conn.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static String displayContent(Connection conn,int id) {
		String content= "";

		String sql= "select content from grammarguideline where grammarguidelineid='"+id+"'";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				content = rs.getString("content");
			}
			conn.close();
			rs.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return content;
	}

	public static List<Listgrammarguidline> phantrang(Connection conn,int start,int count) {
		List<Listgrammarguidline> lg = new ArrayList<Listgrammarguidline>();
		String sql="select * from grammarguideline limit "+(start-1)+", "+count+"";
		
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			
			ResultSet rs  =ps.executeQuery();
			while(rs.next()) {
				Listgrammarguidline l = new Listgrammarguidline();
				l.setGrammarguidlineid(rs.getInt("grammarguidelineid"));
				l.setContent(rs.getString("content"));
				l.setGrammarimage(rs.getString("grammarimage"));
				l.setGrammarname(rs.getString("grammarname"));
				
				lg.add(l);
			}
			
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		return lg;
	}
	
	public static int cout(Connection conn) {
		int cout = 0;
		String sql= "select count(*) from grammarguideline";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			cout = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cout;
	}
	public static void deleteCmtInGrammar(Connection conn, int grammarguidelineid) {
		String sql="delete from cmtgrammar where grammarguidelineid="+grammarguidelineid;
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public static void deleteGrammar(Connection conn,int grammarguidelineid)
	{
		String sql="delete from grammarguideline where grammarguidelineid="+grammarguidelineid;
		
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		}
catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
