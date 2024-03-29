package com.sist.di;

import java.util.*;
import java.sql.*;

public class CommonsDAO {
	private Connection conn;
	private PreparedStatement ps;
	private String url, username, password, driver;
	
	public CommonsDAO(String driver)
	{
		try
		{
			Class.forName(driver);
		}catch(Exception ex) {}
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// 연결
	public Connection getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(url, username, password);
		}catch(Exception ex) {}
		return conn;
	}
	
	// 해제
	public void disConnection(Connection conn, PreparedStatement ps)
	{
		try
		{
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		}catch(Exception ex) {}
	}
	
}
