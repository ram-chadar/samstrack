package com.student;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dbcon.DBUtil;

//import com.dbcon.Conn;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@MultipartConfig
@WebServlet("/uploadsheet")
public class UploadSheet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadSheet() {
		super();
		// TODO Auto-generated constructor stub
	}

	synchronized protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		PrintWriter out=response.getWriter();
		
		Workbook workbook = null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		int inserted = 0, exists = 0;

		try {
			con = DBUtil.getDataSource().getConnection();

			String path = getServletContext().getRealPath("/WEB-INF/uploaded");

			Part part = request.getPart("file");
			InputStream is = part.getInputStream();
			bis = new BufferedInputStream(is);
			fos = new FileOutputStream(new File(path + File.separator + part.getName()));
			int ch = 0;
			while ((ch = bis.read()) != -1) {
				fos.write(ch);
			}

			workbook = Workbook.getWorkbook(new File(path + File.separator + part.getName()));
			Sheet sheet = workbook.getSheet(0);
			int sheetColCount = sheet.getColumns();
			if (sheetColCount != 13) {
				request.setAttribute("msg", "Invalid Data Into Given ExcelSheet Check It (Column Mismatch)");
				RequestDispatcher rd = request.getRequestDispatcher("uploadStudent.jsp");
				rd.forward(request, response);
			} else {

				for (int i = 1; i < sheet.getRows(); i++) {

					Cell accYear = sheet.getCell(0, i);
					Cell branchName = sheet.getCell(1, i);
					Cell year = sheet.getCell(2, i);
					Cell division = sheet.getCell(3, i);
					Cell rollNo = sheet.getCell(4, i);
					Cell studentName = sheet.getCell(5, i);
					Cell gender = sheet.getCell(6, i);
					Cell mail = sheet.getCell(7, i);
					Cell studentPhone = sheet.getCell(8, i);
					Cell address = sheet.getCell(9, i);
					Cell fatherName = sheet.getCell(10, i);
					Cell motherName = sheet.getCell(11, i);
					Cell parentPhone = sheet.getCell(12, i);

					PreparedStatement p = con.prepareStatement(
							"select * from student where accyear=? and branchname=? and year=? and division=? and rollno=?");
					p.setString(1, accYear.getContents());
					p.setString(2, branchName.getContents());
					p.setString(3, year.getContents());
					p.setString(4, division.getContents());
					p.setString(5, rollNo.getContents());

					rs = p.executeQuery();
					if (rs.next()) {
						exists++;
					} else {
						ps = con.prepareStatement(
								"insert into student(accyear,branchname,year,division,rollno,studentname,gender,mail,studentphone,address,fathername,mothername,parentphone)"
										+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
						ps.setString(1, accYear.getContents());
						ps.setString(2, branchName.getContents());
						ps.setString(3, year.getContents());
						ps.setString(4, division.getContents());

						ps.setString(5, rollNo.getContents());
						ps.setString(6, studentName.getContents());
						ps.setString(7, gender.getContents());
						ps.setString(8, mail.getContents());
						ps.setString(9, studentPhone.getContents());

						ps.setString(10, address.getContents());
						ps.setString(11, fatherName.getContents());
						ps.setString(12, motherName.getContents());
						ps.setString(13, parentPhone.getContents());

						int result = ps.executeUpdate();

						if (result > 0) {
							inserted++;
						}

					}
				}
				request.setAttribute("msg", inserted + " " + "Inserted" + " " + exists + " " + "Already Exists");
				RequestDispatcher rd = request.getRequestDispatcher("studentListOption.jsp");
				rd.forward(request, response);
			}
		}

		catch (FileNotFoundException fe) {

			request.setAttribute("msg", "File Not Found ");
			RequestDispatcher rd = request.getRequestDispatcher("studentRegistration.jsp");
			rd.forward(request, response);
		}

		catch (BiffException fileFormat) {
			request.setAttribute("msg", "Invalid file selected, valid files are of .xls type");
			RequestDispatcher rd = request.getRequestDispatcher("uploadStudent.jsp");
			rd.forward(request, response);
		}

		catch (Exception e) {
			e.printStackTrace();
			out.println(e);

		} finally {
			if (workbook != null) {
				workbook.close();
			}
			if (fos != null) {
				fos.close();
			}
			if (bis != null) {
				bis.close();
			}
			try {
				if (con != null) {
					con.close();
				}
				if(ps!=null)
				{
					ps.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				out.println(e2);

			}

		}

	}

}
