package encode;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.onedear.util.db.DBUtil;

  /**
	 * 
	 * %B9%E3%D6%DD%B2%A9%BB%E3
	 * 	 ���ݵ���	广州电信
     *	 ���ݲ���	广州博汇
	 * @param args
	 */
public class TestEncode {
	
	static byte[] sourceBytes = new byte[]{-17, -65, -67, -17, -65, -67, 
			-17, -65, -67, -35, -78, -17, -65, -67, -17, -65, -67, -17, -65, -67};
	
//	static String etStr = "%B9%E3%D6%DD%B2%A9%BB%E3";
	static String etStr = "%B9%E3%D6%DD%B5%E7%D0%C5";
	
	public static void main(String[] args) throws Exception {
		TestEncode t = new TestEncode();
		String value = URLDecoder.decode(etStr, "utf-8");
		System.out.println(value);
		
		
		/*
		System.out.println(value);
//		t.insert(value);
		String result  =t.getValueFromDB(2);
		System.out.println(result);
		String result2 = t.getValueFromValueDB();
		System.out.println(result2);
		*/
	}
	
	public String getValueFromDB(int index) throws ClassNotFoundException, SQLException {
		String selectSsql = "select valuename from dim_Urlargumentvalue_test where id = " + index;
		
		Connection con = DBUtil.getConnection("192.168.0.81:1433", "ETM_2_2_Localhost_96", "sa",
				"password", DBUtil.SQLSERVER);
		PreparedStatement pstat = con.prepareStatement(selectSsql);
		ResultSet rs = pstat.executeQuery();
		rs.next();
		String value = rs.getString(1);
		return value ; 
	}
	
	public String getValueFromValueDB() throws ClassNotFoundException, SQLException {
		String selectSsql = "select valuename from dim_Urlargumentvalue where id = 11" ;
		
		Connection con = DBUtil.getConnection("192.168.0.81:1433", "ETM_2_2_Localhost_96", "sa",
				"password", DBUtil.SQLSERVER);
		PreparedStatement pstat = con.prepareStatement(selectSsql);
		ResultSet rs = pstat.executeQuery();
		rs.next();
		String value = rs.getString(1);
		return value ; 
	}
	
	public void insert(String sourceValue) throws ClassNotFoundException, SQLException {
		Connection con = DBUtil.getConnection("192.168.0.81:1433", "ETM_2_2_Localhost_96", "sa",
				"password", DBUtil.SQLSERVER);
		
		String deleteSql = "truncate table dim_Urlargumentvalue_test";
		
		String insertSql = "insert into dim_Urlargumentvalue_test(valueName) values (N'"+sourceValue+"')";
		System.out.println(insertSql);
		PreparedStatement pstat = con.prepareStatement(deleteSql);
		pstat.execute();
		pstat = con.prepareStatement(insertSql);
		pstat.execute();
		
	}
}
