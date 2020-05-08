package com.student;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.Conn;
import com.dbcon.DBUtil;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


@WebServlet("/PrintStudentReport")
public class PrintStudentReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public PrintStudentReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String accyear=request.getParameter("accyear");
String year=request.getParameter("year");
String branchname=request.getParameter("branchname");
String division=request.getParameter("division");
try {
	Connection con = DBUtil.getDataSource().getConnection();

			HashMap<String, Object> param = new HashMap<String, Object>();

			param.put("year", "SE");
			param.put("accyear","2019");
			param.put("branchname", "COMPUTER ENGINEERING");
			param.put("division", "A");

			@SuppressWarnings("deprecation")
			String jrxmlfile = request.getRealPath("/reports/StudentList.jrxml");
			InputStream input = new FileInputStream(new File(jrxmlfile));

			JasperReport jr = JasperCompileManager.compileReport(input);
			JasperPrint jp = JasperFillManager.fillReport(jr, param, con);
			JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());

			JasperViewer jw = new JasperViewer(jp, false); // if we dont give second parameter false then it stope
			jw.setVisible(true);

			response.getOutputStream().flush();
			response.getOutputStream().close();
			/* RequestDispatcher rd=request.getRequestDispatcher("/home.jsp");
			  rd.forward(request, response);*/
			 

		} catch (Exception exc) {
			System.out.println(exc);
			// out.println(exc);
			// out.println("print page");
		}
	}

}
