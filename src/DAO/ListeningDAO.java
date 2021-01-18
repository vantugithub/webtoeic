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

import BEAN.Listening;
import BEAN.ListeningQuestion;

public class ListeningDAO {
	public static int  getRow(Connection conn) {
		int dem=0;
		String sql="select count(*) from listenexercise";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			dem = rs.getInt(1);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return dem;
	}
	
	public static int  getRow1(Connection conn,int listenexerciseid) {
		int dem=0;
		String sql="select num from listenquestion where listenexerciseid="+listenexerciseid;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				dem++;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return dem;
	}
	
	public static List<Listening> list(Connection conn,int start,int count) {
		List<Listening> list  = new ArrayList<Listening>();
		String sql="select * from listenexercise limit "+(start-1)+", "+count;
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Listening l = new Listening();
				l.setListenexerciseid(rs.getInt("listenexerciseid"));
				l.setListenexerciseimage(rs.getString("listenexerciseimage"));
				l.setListenexercisename(rs.getString("listenexercisename"));
				list.add(l);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static boolean insertListening(Connection conn,String name) {
		String sql="insert into listenexercise(listenexercisename) values(?)";
		try {
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.executeUpdate();
			return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static String uploadSingleFile(Connection conn,HttpServletRequest request,HttpServletResponse response,int listenexerciseid) 
			throws ServletException, IOException {
		String mess="";
		ServletContext context = request.getServletContext();
		final String address = context.getRealPath("/ImageListening");
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
							DAO.ListeningDAO.insertLinkOfImageListening(conn, listenexerciseid, fileName);
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
	
	public static void insertLinkOfImageListening(Connection conn,int listenexerciseid,String name) {
		String sql="update listenexercise set listenexerciseimage=?"+" where listenexerciseid="+listenexerciseid;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,name);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int  getListenexerciseid(Connection conn,String name) {
		int listenexerciseid = 0;
		String sql="select listenexerciseid from listenexercise where listenexercisename='"+name+"'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listenexerciseid = rs.getInt("listenexerciseid");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listenexerciseid;
	}
	
	public static List<Listening> listListening(Connection conn,int start,int count)
	{
		List<Listening> list  = new ArrayList<Listening>();
		String sql="select * from listenexercise limit "+(start-1)+", "+count;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Listening l = new Listening();
				l.setListenexerciseid(rs.getInt("listenexerciseid"));
				l.setListenexerciseimage(rs.getString("listenexerciseimage"));
				l.setListenexercisename(rs.getString("listenexercisename"));
				list.add(l);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static String uploadSingleFileAudioAndImage(Connection conn,HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException {
		String mess="";
		ServletContext context = request.getServletContext();
		final String address = context.getRealPath("/ImageAudioListening");
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
	
	public static List<ListeningQuestion> listListeningQuestion (Connection conn,int listenexerciseid,int start,int count){
		List<ListeningQuestion> list = new ArrayList<ListeningQuestion>();
		String sql= "select * from listenquestion where listenexerciseid= "+listenexerciseid+" limit "+(start-1)+", "+count+"";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			ListeningQuestion listenquestion  = new ListeningQuestion();
			
			int num = rs.getInt("num");
			String imagename  = rs.getString("imagename");
			String audiomp3  = rs.getString("audiomp3");
			String audiogg  = rs.getString("audiogg");
			String question = rs.getString("question");
			String option1 = rs.getString("option1");
			String option2 = rs.getString("option2");
			String option3 = rs.getString("option3");
			String option4 = rs.getString("option4");
		
			
			listenquestion.setNum(num);
			listenquestion.setImagename(imagename);
			listenquestion.setAudiomp3(audiomp3);
			listenquestion.setAudiogg(audiogg);
			listenquestion.setQuestion(question);
			listenquestion.setOption1(option1);
			listenquestion.setOption2(option2);
			listenquestion.setOption3(option3);
			listenquestion.setOption4(option4);
			
			list.add(listenquestion);
		}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<ListeningQuestion> listDapAn(Connection conn,int listenexerciseid,int num ) {
		List<ListeningQuestion> list = new ArrayList<ListeningQuestion>();
		String sql="select * from listenquestion where listenexerciseid= "+listenexerciseid+" and num="+num;
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				ListeningQuestion ls = new ListeningQuestion();
				
				ls.setAudiogg(rs.getString("audiogg"));
				ls.setAudiomp3(rs.getString("audiomp3"));
				ls.setCorrectanswer(rs.getString("correctanswer"));
				ls.setImagename(rs.getString("imagename"));
				ls.setListenexerciseid(rs.getInt("listenexerciseid"));
				ls.setListenquestionid(rs.getInt("listenquestionid"));
				ls.setNum(rs.getInt("num"));
				ls.setOption1(rs.getString("option1"));
				ls.setOption2(rs.getString("option2"));
				ls.setOption3(rs.getString("option3"));
				ls.setOption4(rs.getString("option4"));
				ls.setQuestion(rs.getString("question"));
				
				list.add(ls);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static String uploadSingleFile1(Connection conn,HttpServletRequest request,HttpServletResponse response,int listenexerciseid) 
			throws ServletException, IOException {
		String mess="";
		ServletContext context = request.getServletContext();
		final String address = context.getRealPath("/FileExcelListening");
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
							//		insertLinkOfImageFile(conn,fileName, idGrammarguideline);
							DAO.ListeningDAO.importExcel(request, response, conn, pathFile, listenexerciseid);
							mess="success";
							
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
	
	public static void importExcel(HttpServletRequest request, HttpServletResponse response,Connection conn,String adress,int listenexerciseid) throws ServletException, IOException {

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
				    String imagename =null;
				    String audiogg =null;
				    String audiomp3 =null;
					int num = (int) row.getCell(0).getNumericCellValue();
					if(row.getCell(1)==null) {
						imagename=null;
					}else {
						imagename = row.getCell(1).getStringCellValue();
					}
					if(row.getCell(2)==null) {
						audiogg=null;
					}else {
						audiogg = row.getCell(2).getStringCellValue();
					}
					if(row.getCell(3)==null) {
						audiomp3=null;
					}else {
						audiomp3 = row.getCell(3).getStringCellValue();
					}
					String question = row.getCell(4).getStringCellValue();
					String option1 = row.getCell(5).getStringCellValue();
					String option2 = row.getCell(6).getStringCellValue();
					String option3 = row.getCell(7).getStringCellValue();
					String option4 = row.getCell(8).getStringCellValue();
					String correctanswer = row.getCell(9).getStringCellValue();
					int listenexerciseid1 = listenexerciseid;

					ListeningQuestion ex = new ListeningQuestion();

					ex.setAudiogg(audiogg);
					ex.setAudiomp3(audiomp3);
					ex.setCorrectanswer(correctanswer);
					ex.setListenexerciseid(listenexerciseid1);
					ex.setImagename(imagename);
					ex.setNum(num);
					ex.setOption1(option1);
					ex.setOption2(option2);
					ex.setOption3(option3);
					ex.setOption4(option4);
					ex.setQuestion(question);

					DAO.ListeningDAO.insertFileDeThi(conn, ex);


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
	public static void insertFileDeThi(Connection conn,ListeningQuestion ex) {
		String sql="insert into listenquestion(num,imagename,audiogg,audiomp3,question,option1,option2,option3,option4,correctanswer,listenexerciseid) values(?,?,?,?,?,?,?,?,?,?,?) ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ex.getNum());
			ps.setString(5, ex.getQuestion());
			ps.setString(3, ex.getAudiogg());
			ps.setString(4, ex.getAudiomp3());
			ps.setString(2, ex.getImagename());
			ps.setString(6, ex.getOption1());
			ps.setString(7, ex.getOption2());
			ps.setString(8, ex.getOption3());
			ps.setString(9, ex.getOption4());
			ps.setString(10, ex.getCorrectanswer());
			ps.setInt(11, ex.getListenexerciseid());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
