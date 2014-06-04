package com.onedear.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 数据库小工具类，不关闭链接
 * @author onedear
 *
 */
public class DBManager {
	
	public static final int SQLSERVER = 1;
	public static final int MYSQL = 2;
	public static final int ORACLE = 3;
	private static final String LINE = "\r\n";
	private static final String TAB = "\t";
	
	private Connection con;
	
	private PreparedStatement pstmt;
	
	private ResultSet rs;
	
	public DBManager() {
	}
	
	public DBManager(String ipPort ,String dbname ,String userName ,String password , Integer type) {
		try {
			con = DBUtil.getConnection(ipPort, dbname, userName, password, type);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public DBManager(Connection con) {
		this.con = con;
	}
	
	public ResultSet select(String sql ) {
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void insert(String sql) {
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			rs.close();
		} catch(Exception e) {
		}
		try {
			pstmt.close();
		} catch(Exception e) {
		}
	}
}
