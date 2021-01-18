package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import BEAN.Vocabularycontent;
import BEAN.vocabularyguideline;

public class VocabularyDAO {
	public static boolean insertVocabularyTopics(Connection conn,String vocabularyTopics) {
		String sql= "insert into vocabularyguideline(vocabularyname) values(?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,vocabularyTopics );
			ps.executeUpdate();
			return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static int idVocabularyTopics(Connection conn, String nameCabularyTopics) {
		int id = 0;
		String sql="select vocabularyguidelineid from vocabularyguideline where vocabularyname='"+nameCabularyTopics+"'";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs  = ps.executeQuery();
			
			while(rs.next()) {
				id = rs.getInt("vocabularyguidelineid");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public static String uploadSingleFile1(Connection conn,HttpServletRequest request,HttpServletResponse response,int vocabularyguidelineid) 
			throws ServletException, IOException {
		String mess="";
		ServletContext context = request.getServletContext();
		final String address = context.getRealPath("/ImageVocabulary");
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
							DAO.VocabularyDAO.insertLinkOfImageVocabulary(conn, fileName, vocabularyguidelineid);
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
	
	public static void insertLinkOfImageVocabulary(Connection conn,String fileName,int vocabularyguidelineid ) {
		String sql="update vocabularyguideline set vocabularyimage=? where vocabularyguidelineid="+vocabularyguidelineid;
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, fileName);
			ps.executeUpdate();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static List<BEAN.vocabularyguideline> list (Connection conn){
		List<vocabularyguideline> list  = new ArrayList<vocabularyguideline>();
		String sql="select * from vocabularyguideline";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				vocabularyguideline vc  = new vocabularyguideline();
				
				vc.setVocabularyguidelineid(rs.getInt("vocabularyguidelineid"));
				vc.setVocabularyimage(rs.getString("vocabularyimage"));
				vc.setVocabularyname(rs.getString("vocabularyname"));
				list.add(vc);
			}
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static List<vocabularyguideline> list1 (Connection conn,int start,int count) {
		List<vocabularyguideline> list = new ArrayList<vocabularyguideline>();
		String sql="select * from vocabularyguideline limit "+(start-1)+", "+count;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				vocabularyguideline vc  = new vocabularyguideline();
				vc.setVocabularyguidelineid(rs.getInt("vocabularyguidelineid"));
				vc.setVocabularyimage(rs.getString("vocabularyimage"));
				vc.setVocabularyname(rs.getString("vocabularyname"));
				vc.setStatus(rs.getInt("status"));
				
				list.add(vc);
				
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static int getRow (Connection conn) {
		int dem =0;
		String sql="select count(*) from vocabularyguideline";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			dem= rs.getInt(1);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return dem;
	}
	public static void checkVocabulary(Connection conn,int vocabularyguidelineid) {
		String sql="update vocabularyguideline set status=1 where vocabularyguidelineid="+vocabularyguidelineid;
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void importExcel(HttpServletRequest request, HttpServletResponse response,Connection conn,String adress,int vocabularyguidelineid) throws ServletException, IOException {

		FileInputStream inputStream;
		try {
			// Đọc một file XSL.
			inputStream = new FileInputStream(new File(adress));

			// Đối tượng workbook cho file XSL.
			HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(inputStream));


			// Lấy ra sheet đầu tiên từ workbook
			Sheet sheet = workbook.getSheetAt(0);

			for( int i = 1 ; i <= sheet.getLastRowNum() ; i++ ) {
				Row row = sheet.getRow(i);
				if( row == null) {
					continue;
				}
				else {
					//		    	   Row row = sheet.getRow(i);
				  int num = (int) row.getCell(0).getNumericCellValue();
				  String vocabularycontentname = row.getCell(1).getStringCellValue();
				  String transcribe = row.getCell(2).getStringCellValue();
				  String image = row.getCell(3).getStringCellValue();
				  String audiomp3 = row.getCell(4).getStringCellValue();
				  String audiogg = row.getCell(5).getStringCellValue();
				  String mean = row.getCell(6).getStringCellValue();
				  
				  Vocabularycontent vc = new Vocabularycontent();
				  
				  vc.setAudiogg(audiogg);
				  vc.setAudiomp3(audiomp3);
				  vc.setImage(image);
				  vc.setMean(mean);
				  vc.setNum(num);
				  vc.setTranscribe(transcribe);
				  vc.setVocabularycontentname(vocabularycontentname);
				  vc.setVocabularyguidelineid(vocabularyguidelineid);
				  
				  DAO.VocabularyDAO.insertVocabularycontent(conn, vc);
				}
			}
		} 
		catch (FileNotFoundException e) 
		{
			request.setAttribute("mess",e.getMessage());
		}
		catch (IOException e) 
		{
			request.setAttribute("mess",e.getMessage());
		}

	}
	
	public static void insertVocabularycontent(Connection conn,Vocabularycontent vc) {
		String sql="insert into vocabularycontent(num,vocabularycontentname,transcribe,image,audiomp3,audiogg,mean,vocabularyguidelineid) values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, vc.getNum());
			ps.setString(2, vc.getVocabularycontentname());
			ps.setString(3, vc.getTranscribe());
			ps.setString(4, vc.getImage());
			ps.setString(5, vc.getAudiomp3());
			ps.setString(6, vc.getAudiogg());
			ps.setString(7, vc.getMean());
			ps.setInt(8, vc.getVocabularyguidelineid());
			
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public static String uploadSingleFile(Connection conn,HttpServletRequest request,HttpServletResponse response,int vocabularyguidelineid) 
			throws ServletException, IOException {
		String mess="";
		ServletContext context = request.getServletContext();
		final String address = context.getRealPath("/FileTuVung");
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
							DAO.VocabularyDAO.checkVocabulary(conn, vocabularyguidelineid);
							DAO.VocabularyDAO.importExcel(request, response, conn, pathFile, vocabularyguidelineid);
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
	
	public static List<vocabularyguideline> displayVocabularyTopics(Connection conn,int start,int count){
		List<vocabularyguideline> vc = new ArrayList<vocabularyguideline>();
		String sql="select * from vocabularyguideline limit "+(start-1)+", "+count;
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				vocabularyguideline vcc = new vocabularyguideline();
			vcc.setVocabularyguidelineid(rs.getInt("vocabularyguidelineid"));
			vcc.setVocabularyimage(rs.getString("vocabularyimage"));
			vcc.setVocabularyname(rs.getString("vocabularyname"));
			
			vc.add(vcc);	
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return vc;
	}
	
	public static int getRowVocabularyTopics(Connection conn) {
		int row= 0;
		String sql="select count(*) from vocabularyguideline";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			row = rs.getInt(1);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	
	public static String ThemAudioAndImageVocabulary(Connection conn, HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String test = "";
		ServletContext context = request.getServletContext();
		response.setContentType("text/html; charset=UTF-8");
		
		final String Address = context.getRealPath("Imageaudiotuvung/");
	
		//final String Address = "F://";
		final int MaxMemorySize = 1024 * 1024 * 3; //3MB
		final int MaxRequestSize = 1024 * 1024 * 50; //50 MB
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (!isMultipart)
		{
			test = "Thiếu multipart/form-data trong form";
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		
		// Set factory constraints
		factory.setSizeThreshold(MaxMemorySize);

		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		
		// Set overall request size constraint
		upload.setSizeMax(MaxRequestSize);
		
		
		
		try 
		{
			// Parse the request
			List<FileItem> items = upload.parseRequest(request);
			
			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();
			
			while (iter.hasNext()) 
			{
			    FileItem item = iter.next();

			    if (!item.isFormField()) 
			    {
			    	 String fileName = item.getName();
			    	 
			    	 //pathFile: vị trí mà chúng ta muốn upload file vào
			    	 //gửi cho server
			    	 
			    	String pathFile = Address + File.separator + fileName;
			    	 
			    	File uploadedFile = new File(pathFile);
			    	
			    	
			    	boolean kt = uploadedFile.exists();
			    	 
			    	try 
			    	{
			    		
			    		if (kt == true)
			    		{
			    					    
			    			test = "file tồn tại. Yêu cầu chọn file khác";
			    		}
			    		else
			    		{		    			
			    			item.write(uploadedFile);					    			
			    			test="Success";
			    		}
						
						
					} 
			    	catch (Exception e) 
			    	{ 		
			    		test = e.getMessage();
					}   	 
			    } 
			    else 
			    {
			    	test = "thêm file thất bại";
			    }
			}
			
		} 
		catch (FileUploadException e) 
		{
			test = e.getMessage();
		}
		
		return test;
	}
	public static List<Vocabularycontent> vocabularyContent(Connection conn,int vocabularyguidelineid){
		List<Vocabularycontent> list  = new ArrayList<Vocabularycontent>();
		String sql="select * from vocabularycontent where vocabularyguidelineid="+vocabularyguidelineid;
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Vocabularycontent vc = new Vocabularycontent();
				vc.setAudiogg(rs.getString("audiogg"));
				vc.setAudiomp3(rs.getString("audiomp3"));
				vc.setImage(rs.getString("image"));
				vc.setMean(rs.getString("mean"));
				vc.setNum(rs.getInt("num"));
				vc.setTranscribe(rs.getString("transcribe"));
				vc.setVocabularycontentname(rs.getString("vocabularycontentname"));
				vc.setVocabularyguidelineid(rs.getInt("vocabularyguidelineid"));
				
				list.add(vc);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
