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

import BEAN.Examination;
import BEAN.Examinationquestion;

public class ExamDAO {
	public static boolean addExamName(Connection conn,String name) {
		String sql="insert into examination(examinationame) values(?)";


		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.setString(1, name);
			int kt = ps.executeUpdate();
			if(kt!=0) {
				return true;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static int getExamId(Connection conn,String name) {
		int id =0;
		String sql="select examinationid from examination where examinationame='"+name+"'";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				id = rs.getInt("examinationid");
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public static String uploadSingleFile(Connection conn,HttpServletRequest request,HttpServletResponse response,int idGrammarguideline) 
			throws ServletException, IOException {
		String mess="";
		ServletContext context = request.getServletContext();
		final String address = context.getRealPath("/ImagedExam");
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

	public static void insertLinkOfImageFile(Connection conn,String filename ,int examinationid) {

		String sql="update examination set examinatioimage=? where examinationid=?";
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.setString(1,filename);
			ps.setInt(2, examinationid);
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static List<Examination> DisplayExamination(Connection conn) {
		List<Examination> ex = new ArrayList<Examination>();
		String sql="select * from examination";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Examination x = new Examination();
				x.setExaminationid(rs.getInt("examinationid"));
				x.setExaminationame(rs.getString("examinationame"));
				x.setExaminationage(rs.getString("examinationage"));
				ex.add(x);
			}

		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ex;
	}
	public static List<Examination> pagination(Connection conn, int start,int count) {
		List<Examination> ex = new ArrayList<Examination>();
		String sql="select * from examination limit "+(start-1)+", "+count+"";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Examination e = new Examination();
				e.setExaminationid(rs.getInt("examinationid"));
				e.setExaminationame(rs.getString("examinationame"));
				e.setExaminationage(rs.getString("examinatioimage"));
				e.setChecked(rs.getInt("checkedcauhoi"));
				ex.add(e);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return ex;
	}
	public static int countRow (Connection conn) {
		int dem=0;
		String sql="select count(*) where examination";

		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			dem = rs.getInt("examinationid");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dem;
	}

	public static void importExcel(HttpServletRequest request, HttpServletResponse response,Connection conn,String adress,int examinationid) throws ServletException, IOException {

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
				    String paragraph = null;
				    String imagequestion =null;
				    String audiogg =null;
				    String audiomp3 =null;
					int num = (int) row.getCell(0).getNumericCellValue();
					if(row.getCell(1)==null) {
						imagequestion=null;
					}else {
						imagequestion = row.getCell(1).getStringCellValue();
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
					if(row.getCell(4)==null) {
						paragraph=null;
					}else {
						paragraph = row.getCell(4).getStringCellValue();
					}
					String question = row.getCell(5).getStringCellValue();
					String option1 = row.getCell(6).getStringCellValue();
					String option2 = row.getCell(7).getStringCellValue();
					String option3 = row.getCell(8).getStringCellValue();
					String option4 = row.getCell(9).getStringCellValue();
					String correctanswer = row.getCell(10).getStringCellValue();
					int examinationid1 = examinationid;

					Examinationquestion ex = new Examinationquestion();

					ex.setAudiogg(audiogg);
					ex.setAudiomp3(audiomp3);
					ex.setCorrectanswer(correctanswer);
					ex.setExaminationid(examinationid1);
					ex.setImagequestion(imagequestion);
					ex.setNum(num);
					ex.setOption1(option1);
					ex.setOption2(option2);
					ex.setOption3(option3);
					ex.setOption4(option4);
					ex.setParagraph(paragraph);
					ex.setQuestion(question);

					DAO.ExamDAO.insertFileDeThi(conn, ex);


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

	public static void insertFileDeThi(Connection conn,Examinationquestion ex) {
		String sql="insert into examinationquestion(num,imagequestion,audiogg,audiomp3,paragraph,question,option1,option2,option3,option4,correctanswer,examinationid) values(?,?,?,?,?,?,?,?,?,?,?,?) ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ex.getNum());
			ps.setString(2, ex.getImagequestion());
			ps.setString(3, ex.getAudiogg());
			ps.setString(4, ex.getAudiomp3());
			ps.setString(5, ex.getParagraph());
			ps.setString(6, ex.getQuestion());
			ps.setString(7, ex.getOption1());
			ps.setString(8, ex.getOption2());
			ps.setString(9, ex.getOption3());
			ps.setString(10, ex.getOption4());
			ps.setString(11, ex.getCorrectanswer());
			ps.setInt(12, ex.getExaminationid());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String uploadSingleFile1(Connection conn,HttpServletRequest request,HttpServletResponse response,int examinationid) 
			throws ServletException, IOException {
		String mess="";
		ServletContext context = request.getServletContext();
		final String address = context.getRealPath("/FileDethi");
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
							DAO.ExamDAO.importExcel(request, response, conn, pathFile, examinationid);
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
	
	public static void checked(Connection conn,int examinationid) {
		String sql="update examination set checked="+1+" where examinationid="+examinationid;
		
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String Themaudiohinhanhdethi(Connection conn, HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String test = "";
		ServletContext context = request.getServletContext();
		response.setContentType("text/html; charset=UTF-8");
		
		final String Address = context.getRealPath("Imageaudiodethi/");
	
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

}
