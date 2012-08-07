package com.onedear.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.onedear.util.FileUtils;
import com.onedear.util.database.DataBaseType;

/**
 * 数据库工具类
 * 
 * @author onedear
 * @data:2010-10-21 下午06:12:39
 */
public class DBUtil {

	public static final int SQLSERVER = 1;
	public static final int MYSQL = 2;
	public static final int ORACLE = 3;
	private static final String LINE = "\r\n";
	private static final String TAB = "\t";

	/**
	 * 用于调试,懒得写相应的参数
	 * 
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
			return getConnection("192.168.0.96:1433", "ETM_2_2_Localhost", "sa",
					"password", DBUtil.SQLSERVER);
	}

	/**
	 * 
	 * 通过jdbc获取相应的数据库链接connection
	 * 
	 * @param ipport
	 *            ip+port ,eg.: 192.168.0.161:1997
	 * @param dbName
	 *            databaseName ,eg. : ETForMonitor_V2
	 * @param username
	 *            eg.:sa
	 * @param password
	 *            eg. :password
	 * @param type
	 *            请看本类的静态变量
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection(String ipport, String dbName,
			String username, String password, int type)
			throws ClassNotFoundException, SQLException {
		String jdbcString = null;
		if (type == SQLSERVER) {
			jdbcString = "jdbc:jtds:sqlserver://" + ipport + ";databaseName="
					+ dbName;
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} else if (type == MYSQL) {
			jdbcString = "jdbc:mysql://" + ipport + "/" + dbName;
			Class.forName("org.gjt.mm.mysql.Driver");
		} else if (type == ORACLE) {
			jdbcString = "jdbc:oracle:thin:@" + ipport + ":" + dbName;
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}

		Connection connection = null;
		connection = DriverManager
				.getConnection(jdbcString, username, password);
		return connection;
	}

	public static String table2pojo(Connection connection, String tableName,
			int dbType, String path , boolean isCreateFile) throws SQLException {
		String sql = "select * from " + tableName + " where 1 <> 1";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();
		ResultSetMetaData md = rs.getMetaData();
		int columnCount = md.getColumnCount();
		
		StringBuffer sb = new StringBuffer();
		tableName = tableName.substring(0, 1).toUpperCase() +tableName.subSequence(1, tableName.length());
		sb.append("public class " + tableName + " {");
		sb.append(LINE);

		for (int i = 1; i <= columnCount; i++) {
			sb.append(TAB);
			sb.append("private "
					+ DataBaseType.getPojoType(md.getColumnTypeName(i)) + " "
					+ md.getColumnName(i) + ";");
			sb.append(LINE);
		}

		for (int i = 1; i <= columnCount; i++) {
			sb.append(TAB);

			String pojoType = DataBaseType.getPojoType(md.getColumnTypeName(i));
			String columnName = md.getColumnName(i);
			String getName = null;
			String setName = null;
			if (columnName.length() > 1) {
				getName = "public "+pojoType+" get" + columnName.substring(0, 1).toUpperCase()
						+ columnName.substring(1, columnName.length()) + "() {";
				setName = "public void set" + columnName.substring(0, 1).toUpperCase()
						+ columnName.substring(1, columnName.length()) + "("
						+ pojoType + " " + columnName + ") {";
			} else {
				getName = "public get" + columnName.toUpperCase() + "() {";
				setName = "public set" + columnName.toUpperCase() + "(" + pojoType
						+ " " + columnName + ") {";
			}
			
			sb.append(LINE).append(TAB).append(getName);
			sb.append(LINE).append(TAB).append(TAB);
			sb.append("return " + columnName +";");
			sb.append(LINE).append(TAB).append("}");
			sb.append(LINE);
			sb.append(LINE).append(TAB).append(setName);
			sb.append(LINE).append(TAB).append(TAB);
			sb.append("this." +  columnName + " = " + columnName +";" );
			sb.append(LINE).append(TAB).append("}");
			sb.append(LINE);
			
		}
		sb.append("}");

		System.out.println(sb.toString());
		
		if(isCreateFile)
			FileUtils.stringToFile(null,tableName +".java" , sb.toString());
		return null;
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Connection con = getConnection();
		table2pojo(con, "dim_BasicProbe", DBUtil.SQLSERVER, "" , true);
		
	}


}
