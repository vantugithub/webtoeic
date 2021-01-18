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

import BEAN.Reading;
import BEAN.ReadingQuestion;

public class ReadDAO {
	public static List<Reading> displayRead(Connection conn,int start,int count){
		List<Reading> list  = new ArrayList<Reading>();
		String sql = "select * from readexercise limit "+(start-1)+", "+count;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Reading re  = new Reading();
				re.setReadexerciseid(rs.getInt("readexerciseid"));
				re.setReadimage(rs.getString("readimage"));
				re.setReadname(rs.getString("readname"));
				re.setStatus(rs.getInt("status"));
				list.add(re);
			}
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public static int getRow(Connection conn) {
		int dem = 0;
		String sql="select count(*) from readexercise";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			dem= rs.getInt(1);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return dem;
	}
	
	public static boolean insertReading(Connection conn,String readname) {
		String sql="insert into readexercise(readname) values(?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, readname);
			ps.executeUpdate();
			return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static int getReadexerciseid(Connection conn, String readname) {
		int id  = 0 ;
		String sql="select readexerciseid from readexercise where readname='"+readname+"'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("readexerciseid");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public static String uploadSingleFileReading(Connection conn,HttpServletRequest request,HttpServletResponse response,int readexerciseid) 
			throws ServletException, IOException {
		String mess="";
		ServletContext context = request.getServletContext();
		final String address = context.getRealPath("/ImageReading");
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
						//	DAO.VocabularyDAO.insertLinkOfImageVocabulary(conn, fileName, vocabularyguidelineid);
							DAO.ReadDAO.insertLinkOfImageReading(conn, fileName, readexerciseid);
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
	
	public static void insertLinkOfImageReading(Connection conn,String filename,int readexerciseid) {
		String sql="update readexercise set readimage=? where readexerciseid="+readexerciseid;
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.setString(1, filename);
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static List<Reading> displayFrontReading(Connection conn,int start,int count) {
		List<Reading> list  = new ArrayList<Reading>();
		String sql="select * from readexercise limit "+(start-1)+", "+count;
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Reading reading = new Reading();
				reading.setReadexerciseid(rs.getInt("readexerciseid"));
				reading.setReadimage(rs.getString("readimage"));
				reading.setReadname(rs.getString("readname"));
				
				list.add(reading);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static void importExcel(HttpServletRequest request, HttpServletResponse response,Connection conn,String adress,int readexerciseid) throws ServletException, IOException {

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
					int num = (int)row.getCell(0).getNumericCellValue();
					String paragraph = null;
					if(row.getCell(1)==null) {
						paragraph = null;
					}else {
						paragraph= row.getCell(1).getStringCellValue();
					}
					
					String question = row.getCell(2).getStringCellValue();
					String option1 = row.getCell(3).getStringCellValue();
					String option2 = row.getCell(4).getStringCellValue();
					String option3 = row.getCell(5).getStringCellValue();
					String option4 = row.getCell(6).getStringCellValue();
					String correctanswer = row.getCell(7).getStringCellValue();
					int readexerciseid1 = readexerciseid;
					
					ReadingQuestion rq = new ReadingQuestion();
					
					rq.setCorrectanswer(correctanswer);
					rq.setNum(num);
					rq.setOption1(option1);
					rq.setOption2(option2);
					rq.setOption3(option3);
					rq.setOption4(option4);
					rq.setParagraph(paragraph);
					rq.setQuestion(question);
					rq.setReadexerciseid(readexerciseid1);
					
					DAO.ReadDAO.insertReadingQuestion(conn, rq);
					
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
	
	public static void insertReadingQuestion(Connection conn,ReadingQuestion rq) {
		String sql="insert into readquestion(num,paragraph,question,option1,option2,option3,option4,correctanswer,readexerciseid) values(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, rq.getNum());
				ps.setString(2, rq.getParagraph());
				ps.setString(3, rq.getQuestion());
				ps.setString(4, rq.getOption1());
				ps.setString(5, rq.getOption2());
				ps.setString(6, rq.getOption3());
				ps.setString(7, rq.getOption4());
				ps.setString(8, rq.getCorrectanswer());
				ps.setInt(9, rq.getReadexerciseid());
				ps.executeUpdate();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String uploadSingleFileReadingExcel(Connection conn,HttpServletRequest request,HttpServletResponse response,int readexerciseid) 
			throws ServletException, IOException {
		String mess="";
		ServletContext context = request.getServletContext();
		final String address = context.getRealPath("/FileExcelReading");
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
							DAO.ReadDAO.importExcel(request, response, conn, pathFile, readexerciseid);
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
	
	public static List<ReadingQuestion> listQuestion(Connection conn,int readexerciseid ){
		List<ReadingQuestion> list  = new ArrayList<ReadingQuestion>();
		String sql="select * from readquestion where readexerciseid="+readexerciseid;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ReadingQuestion rq = new ReadingQuestion();
				rq.setCorrectanswer(rs.getString("correctanswer"));
				rq.setNum(rs.getInt("num"));
				rq.setOption1(rs.getString("option1"));
				rq.setOption2(rs.getString("option2"));
				rq.setOption3(rs.getString("option3"));
				rq.setOption4(rs.getString("option4"));
				rq.setParagraph(rs.getString("paragraph"));
				rq.setQuestion(rs.getString("question"));
				rq.setReadexerciseid(rs.getInt("readexerciseid"));
				list.add(rq);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<ReadingQuestion> listQ(Connection conn,int start,int count,int readexerciseid) {
		List<ReadingQuestion>list  = new ArrayList<ReadingQuestion>();
		String sql="select * from readquestion where readexerciseid="+readexerciseid+" limit "+(start-1)+", "+count;
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				ReadingQuestion rq = new ReadingQuestion();
				rq.setCorrectanswer(rs.getString("correctanswer"));
				rq.setNum(rs.getInt("num"));
				rq.setOption1(rs.getString("option1"));
				rq.setOption2(rs.getString("option2"));
				rq.setOption3(rs.getString("option3"));
				rq.setOption4(rs.getString("option4"));
				rq.setParagraph(rs.getString("paragraph"));
				rq.setQuestion(rs.getString("question"));
				rq.setReadexerciseid(rs.getInt("readexerciseid"));
				list.add(rq);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static int getRowQuestion(Connection conn,int readexerciseid ) {
		int dem=0;
		String sql="select * from readquestion where readexerciseid="+readexerciseid;
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
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
	
	public static List<ReadingQuestion> listDapAn(Connection conn,int readexerciseid,int num ) {
		List<ReadingQuestion>list  = new ArrayList<ReadingQuestion>();
		String sql="select * from readquestion where readexerciseid="+readexerciseid+" and num="+num;
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				ReadingQuestion rq = new ReadingQuestion();
				rq.setCorrectanswer(rs.getString("correctanswer"));
				rq.setNum(rs.getInt("num"));
				rq.setOption1(rs.getString("option1"));
				rq.setOption2(rs.getString("option2"));
				rq.setOption3(rs.getString("option3"));
				rq.setOption4(rs.getString("option4"));
				rq.setParagraph(rs.getString("paragraph"));
				rq.setQuestion(rs.getString("question"));
				rq.setReadexerciseid(rs.getInt("readexerciseid"));
				list.add(rq);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
