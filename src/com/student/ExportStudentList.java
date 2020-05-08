/*package com.student;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.Conn;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

@WebServlet("/exportStudentList")
public class ExportStudentList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection con=null;
		
		String accYear,year,division,branch;
		accYear=request.getParameter("accYear");
	year=request.getParameter("year");
	division=request.getParameter("division");
	branch=request.getParameter("branch");
		try {
			con = Conn.getCon();
			HashMap<String, Object> param = new HashMap<String, Object>();

			param.put("accYear", accYear);
			param.put("year", year);
			param.put("branch", branch);
			param.put("division",division);

			
			 * InputStream is=ClassLoader.getSystemResourceAsStream("ListStudent.jasper");
			 * JasperPrint jp=JasperFillManager.fillReport(is, param,con); JasperViewer
			 * jw=new JasperViewer(jp); jw.setVisible(true);
			 
		

			@SuppressWarnings("deprecation")
			String jrxmlfile = request.getRealPath("/reports/ListStudent.jrxml");
			InputStream input = new FileInputStream(new File(jrxmlfile));

			JasperReport jr = JasperCompileManager.compileReport(input);
			JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
			//JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());
			JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());

			JasperViewer jw = new JasperViewer(jp, false); // if we dont give second parameter false then it stope
															// the tomcat
															// when u closing jasper report
			jw.setVisible(true);

			response.getOutputStream().flush();
			response.getOutputStream().close();
			
			  RequestDispatcher rd=request.getRequestDispatcher("/export.jsp");
			  rd.forward(request, response);
			 

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
*/