package com.attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dbcon.DBUtil;

public class Theory_Attendance_Operation {

	protected static boolean checkAttendance(String branchName, String sem, String division, String subject,
			String dateTime) {

		boolean b = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getDataSource().getConnection();
			ps = con.prepareStatement(
					"select * from classattendance where datetime=? and branch=? and division=? and sem=? and subject=?");
			ps.setString(1, dateTime);
			ps.setString(2, branchName);
			ps.setString(3, division);
			ps.setString(4, sem);
			ps.setString(5, subject);
			rs = ps.executeQuery();
			if (rs.next()) {
				b = true;
			} else {
				b = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return b;
	}

	protected static boolean deleteAttendance(String branchName, String sem, String division, String subject,
			String dateTime) {

		boolean b = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getDataSource().getConnection();
			ps = con.prepareStatement(
					"DELETE FROM classattendance WHERE branch=? and sem=? and division=? and subject=? and datetime=?");
			ps.setString(1, branchName);
			ps.setString(2, sem);
			ps.setString(3, division);
			ps.setString(4, subject);
			ps.setString(5, dateTime);

			int rows = ps.executeUpdate();
			if (rows > 0) {
				b = true;
			} else {
				b = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (con != null)
					con.close();

				if (ps != null)
					ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return b;
	}
	
	
	

}
