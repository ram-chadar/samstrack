package com.dbcon;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBUtil {

	private static BasicDataSource dataSource;
	
	static{
		try {
			
			dataSource = new BasicDataSource();
			
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://localhost:3306/sams");
			dataSource.setUsername("root");
			dataSource.setPassword("admin");
			
			dataSource.setMinIdle(10);
			dataSource.setMaxIdle(-1);
			dataSource.setRemoveAbandonedTimeout(300);
			dataSource.setMaxTotal(10);
			dataSource.setMaxWaitMillis(1000);
			dataSource.setDefaultAutoCommit(true);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DataSource getDataSource(){
		return dataSource;
		
	}
}