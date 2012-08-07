package db;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.onedear.util.FileUtils;
import com.onedear.util.db.DBUtil;

public class TestDBLengthToEt {
	private static final String LINE = "\r\n";
	
	public static Map<String , String> matchMap = new HashMap<String , String> ();
	static {
		matchMap.put("webTag", "appSystem");
		matchMap.put("system", "systems");
		matchMap.put("ptid", "uuid");
		matchMap.put("etusername", "loginname");
		matchMap.put("plugin", "Plugins");
		matchMap.put("sound", "soundModel");
		matchMap.put("camera", "cameraModel");
		matchMap.put("mic", "micModel");
		matchMap.put("referrer", "referer");
		matchMap.put("mouseclick", "posSet");
		matchMap.put("mousemove", "mouseMove");
		matchMap.put("keyboard", "keyboardBeat");
		matchMap.put("keyword", "keyWordsReferer");  
		matchMap.put("ip", "userHostAddress");
		matchMap.put("userAgent", "userAragent");
		matchMap.put("browserMainVersion", ""); //50
		matchMap.put("province", ""); //128
		matchMap.put("provinceCode", "");//128
		matchMap.put("city", "");//128
		matchMap.put("domain", "appDomain");
		matchMap.put("errorContent", "context"); //max
		matchMap.put("errorHttpCode", "httpCode");
		matchMap.put("flashVersionType", ""); //50
		matchMap.put("osType", "");//50
		matchMap.put("urlParams", ""); //2048
		matchMap.put("refferUrlParams", ""); //2048
		matchMap.put("country", "");//128
		matchMap.put("title", "urlTitle");
		matchMap.put("color", "colors");
		matchMap.put("screen", ""); //50
		matchMap.put("errorName","contextType" );
	}
	
	public static Map<String , Integer> test(Connection connection, String tableName,
			int dbType, String path ) throws SQLException {
		Map<String , Integer> dbMap = new HashMap<String , Integer>();
		String sql = "select * from " + tableName + " where 1 <> 1";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();
		ResultSetMetaData md = rs.getMetaData();
		int columnCount = md.getColumnCount();
		
		StringBuffer sb = new StringBuffer();
		
		for (int i = 1; i <= columnCount; i++) {
			String columnName =md.getColumnName(i);
			
			if(!md.getColumnTypeName(i).equals("varchar") &&
					!md.getColumnTypeName(i).equals("nvarchar")) 
				continue;
			
			
			
			columnName = lowerTheFirstWord(columnName);
			columnName=columnName.toLowerCase();
			dbMap.put(columnName, md.getPrecision(i));
//			sb.append(columnName).append("=").append(md.getPrecision(i));
//			sb.append(LINE);
		}
		
		return dbMap;
	}
	
	public static List<String> getClassFields(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<String> classFieldList = new ArrayList<String>();
		Class c;
		c = Class.forName(className);
		Method[] methods = c.getMethods();
		for(Method method : methods) {
			
			Class returnClass = method.getReturnType();
			String returnClassName = returnClass.getName();
			if(!returnClassName.equals("java.lang.String"))
				continue;
			
			String name = method.getName();
			if(!name.startsWith("get"))
				continue;
			name = name.substring(3,name.length());
			name = lowerTheFirstWord(name);
			classFieldList.add(name);
			
			
//			System.out.println(name);
		}
		return classFieldList;
		
	}
	
	public static String lowerTheFirstWord(String word) {
		return word.substring(0,1).toLowerCase() + word.substring(1,word.length());
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Connection con = DBUtil.getConnection();
		Map<String , Integer> map = test(con, "dim_BasicProbe", DBUtil.SQLSERVER, "" );
		List<String> list= getClassFields("db.PageTransactionMessage");
		List<String> removeList = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		
		for(String field : list) {
			if(map.containsKey(field.toLowerCase())) {
				sb.append(field).append("=").append(map.get(field.toLowerCase())).append(LINE);
				removeList.add(field);
			}else if(matchMap.get(field) != null && map.containsKey(matchMap.get(field).toLowerCase())) {
				sb.append(field).append("=").append(map.get(matchMap.get(field).toLowerCase())).append(LINE);
				removeList.add(field);
			}
		}
		System.out.println("总数据是"+ list.size()+"条，匹配得有"+removeList.size()+"条");
		for(String field : removeList) {
			list.remove(field);
			map.remove(field);
		}
		removeList = null;
		
		sb.append(LINE).append(LINE);
		sb.append("无法匹配的类属性").append(LINE);
		for(String field : list) {
			sb.append(field).append(LINE);
		}
//		sb.append("数据库的字段").append(LINE);
//		Set<Map.Entry<String, Integer>> set = map.entrySet();
//		Iterator<Map.Entry<String, Integer>> iterator = set.iterator();
//		while(iterator.hasNext()) {
//			Map.Entry<String, Integer> entry = iterator.next();
//			sb.append(entry.getKey()).append("=").append(entry.getValue()).append(LINE);
//		}
		
		FileUtils.stringToFile(null,"db.txt" , sb.toString());
		
	}
	
	
}
