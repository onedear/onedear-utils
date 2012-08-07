package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.onedear.util.FileUtils;
import com.onedear.util.db.DBUtil;

public class TestOracleFunctionProceDure {
	
//	private static String sql = "select  dbms_metadata.GET_DDL('FUNCTION',u.object_name) from   user_objects u where  object_type = 'FUNCTION'";
	private static String sql = "select  dbms_metadata.GET_DDL('PROCEDURE',u.object_name) from  user_objects u where  object_type = 'PROCEDURE' ";
	
	
	public static void main(String[] args) throws Exception {
		Connection con = DBUtil.getConnection("192.168.0.161:1521", "easyflow", "easyflow", "password", DBUtil.ORACLE);
		System.out.println(con);
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<String> list = new ArrayList<String>();
		while(rs.next()) {
			System.out.println(rs.getString(1));
			list.add(rs.getString(1));
			System.out.println("______________________________________");
		}
		FileUtils.listToFile("c:/procedure.sql", list);
	}
}
