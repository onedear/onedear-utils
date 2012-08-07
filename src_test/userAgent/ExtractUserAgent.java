package userAgent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.onedear.util.db.DBUtil;
import com.onedear.util.http.MobileUtil;
import com.onedear.util.http.MobileVersion;

public class ExtractUserAgent {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ExtractUserAgent e = new ExtractUserAgent();
		
		e.truncateTable();
		
		List<String> list = e.getUserAgentList();
		
		List<TBUserAgent> list2 = e.extractRobinUserAgent(list);
		
		e.insertUserAgentList(list2);
		
		System.out.println("end");
	}
	
	public void truncateTable() throws ClassNotFoundException, SQLException {
		
		Connection connection = DBUtil.getConnection("192.168.0.81:1433", "ODTest", "sa", "password", DBUtil.SQLSERVER);
		String insertSql = "truncate table tbUserAgent";
		PreparedStatement psmt=connection.prepareStatement(insertSql);
		psmt.execute();
	}
	
	
	public List<String> getUserAgentList() throws ClassNotFoundException, SQLException {
		Connection connection = DBUtil.getConnection("192.168.0.82:1433", "phone-ad", "read", "botwave", DBUtil.SQLSERVER);
		String sql = "select userAgentExt from tmp_zhUserAgent";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();
		List<String> list = new ArrayList<String> ();
		while(rs.next()) {
			String userAgent =rs.getString(1);
			list.add(userAgent);
		}
		return list ; 
	}
	
	public void insertUserAgentList(List<TBUserAgent> list) throws ClassNotFoundException, SQLException {
		Connection connection = DBUtil.getConnection("192.168.0.81:1433", "ODTest", "sa", "password", DBUtil.SQLSERVER);
		String insertSql = "insert into tbUserAgent(userAgent , mobile , osType , osVersion , browserType , browserVersion) values(?,?,?,?,?,?)";
		PreparedStatement psmt=connection.prepareStatement(insertSql);
		for(TBUserAgent tbUserAgent : list) {
			psmt.setString(1,tbUserAgent.getUserAgent() );
			psmt.setString(2,tbUserAgent.getMobile() );
			psmt.setString(3,tbUserAgent.getOsType() );
			psmt.setString(4,tbUserAgent.getOsVersion() );
			psmt.setString(5,tbUserAgent.getBrowserType() );
			psmt.setString(6,tbUserAgent.getBrowserVersion() );
			psmt.addBatch();
		}
		psmt.executeBatch(); 
//		connection.commit(); 
		connection.close();  
	}
	
	public List<TBUserAgent> extractRobinUserAgent(List<String> userAgentList) {
		List<TBUserAgent> list = new ArrayList<TBUserAgent>();
		for(String userAgent : userAgentList) {
			TBUserAgent tbUserAgent = new TBUserAgent();
			
			String mobile = MobileUtil.getMobileModle(userAgent);
//			System.out.println("手机品牌是： " + mobile );
			tbUserAgent.setMobile(mobile);
			tbUserAgent.setUserAgent(userAgent);
			MobileVersion mobileOs = 	MobileUtil.getMobileOs(userAgent);
			if(mobileOs != null) {
//				System.out.println("手机系统类型是： " +mobileOs.getType() );
				tbUserAgent.setOsType(mobileOs.getType());
//				System.out.println("手机系统版本号是： " +mobileOs.getVersion());
				tbUserAgent.setOsVersion(mobileOs.getVersion());
			} else {
//				System.out.println("手机系统获取错误");
			}
			
			
			MobileVersion mobileBrowser = 	MobileUtil.getMobileBrowser(userAgent);
			if(mobileBrowser != null) {
//				System.out.println("手机浏览器类型是： " +mobileBrowser.getType() );
				tbUserAgent.setBrowserType(mobileBrowser.getType());
//				System.out.println("手机浏览器版本号是： " +mobileBrowser.getVersion());
				tbUserAgent.setBrowserVersion(mobileBrowser.getVersion());
			} else {
//				System.out.println("手机浏览器获取出错");
			}
			list.add(tbUserAgent);
		}
		return list ; 
	}
}

class TBUserAgent {
	private String userAgent ;
	
	private String mobile ; 
	
	private String osType ;
	
	private String osVersion ; 
	
	private String browserType ; 
	
	private String browserVersion ;

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getBrowserType() {
		return browserType;
	}

	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	} 
}
