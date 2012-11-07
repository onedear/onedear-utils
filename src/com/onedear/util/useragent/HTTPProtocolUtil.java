package com.onedear.util.useragent;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public final class HTTPProtocolUtil {
	
//	private final static String UNKNOWN_VALUE = "未知";
	
	/**
	 * 获取系统类别
	 * 
	 * @param userAgent
	 * @return
	 */
	public static String getOSFromUserAgent(String userAgent) {
		if(userAgent == null)
			return null;
		userAgent = userAgent.toLowerCase();
		String osVersion = "";
		if (userAgent.contains("nt 6.2")) {
			osVersion = "Windows 8";
		} else if (userAgent.contains("nt 6.1")) {
			osVersion = "Windows 7";
		} else if (userAgent.contains("nt 6.0")) {
			osVersion = "Windows Vista";
		} else if (userAgent.contains("android")) {
			osVersion = "Android";
		} else if (userAgent.contains("iphone") ||userAgent.contains("ipad")||userAgent.contains("ipod")) {
			osVersion = "Iphone os";
		} else if (userAgent.contains("nt 5.2")) {
			osVersion = "Windows Server 2003";
		} else if (userAgent.contains("nt 5.1")) {
			osVersion = "Windows XP";
		} else if (userAgent.contains("nt 5")) {
			osVersion = "Windows 2000";
		} else if (userAgent.contains("nt 4")) {
			osVersion = "Windows NT4";
		} else if (userAgent.contains("mac")) {
			osVersion = "Mac";
		} else if (userAgent.contains("unix")) {
			osVersion = "UNIX";
		} else if (userAgent.contains("linux")) {
			osVersion = "Linux";
		} else if (userAgent.contains("sunos")) {
			osVersion = "SunOS";
		} else if (userAgent.contains("me")) {
			osVersion = "Windows Me";
		} else if (userAgent.contains("98")) {
			osVersion = "Windows 98";
		} else if (userAgent.contains("95")) {
			osVersion = "Windows 95";
		} else {
			osVersion = null;
		}
		return osVersion;
	}

	public static String getOsTypeFromOs(String system) {
		if(StringUtil.isEmpty(system))
			return null;
		if (system.toLowerCase().startsWith("windows"))
			return "windows";
		else if (system.toLowerCase().startsWith("linux"))
			return "linux";
		else if (system.toLowerCase().startsWith("mac"))
			return "mac";
		else if (system.toLowerCase().startsWith("sunos"))
			return "sunos";
		else if (system.toLowerCase().startsWith("iphone os"))
			return "iphone os";
		else if (system.toLowerCase().startsWith("android"))
			return "android";
		return null;
	}

	public static String getLanguage(String language) {
		if(StringUtil.isEmpty(language))
			return null;
		language = language.toLowerCase();
		int index = language.indexOf(",") ;
		if(index != -1) 
			language =  language.substring(0 , index);
		index = language.indexOf(";") ;
		if(index != -1) 
			language =  language.substring(0 , index);
		return language ; 
	}
	
	/**
	 * 返回浏览器类型
	 * 
	 * @param userAgent
	 * @return
	 */
	public static String getBrowserFromUserAgent(String userAgent) {
		if(StringUtil.isEmpty(userAgent))
			return null;
		userAgent = userAgent.toLowerCase();
		if (userAgent.indexOf("msie") > -1)
			return "ie";
		else if (userAgent.indexOf("chrome") > -1)
			return "chrome";
		else if (userAgent.indexOf("firefox") > -1)
			return "firefox";
		else if (userAgent.indexOf("opera") > -1)
			return "opera";
		else if (userAgent.indexOf("safari") > -1)
			return "safari";
		else if (userAgent.indexOf("prism") > -1)
			return "prism";
		return null;
	}
	
	/**
	 * 获取浏览器版本，例如返回firefox的3.6.6版本
	 * 
	 * @param userAgent
	 * @param browser
	 *            浏览器类型
	 * @return
	 */
	public static String getBrowserVersionFromUserAgent(String userAgent,
			String browser) {
		if (StringUtil.isEmpty(browser))
			return "";
		if(StringUtil.isEmpty(userAgent))
			return "";
		userAgent = userAgent.toLowerCase();
		int length = userAgent.length();
		int begin = -1 ;
		
		String versionKey = browser ; 
		
		if(browser.equals("safari"))
			versionKey = "version";
		begin = userAgent.indexOf(versionKey);
		if (begin == -1)
			return "";
		begin = begin + versionKey.length();
		int end = begin + 2;
		for (int i = 2; i < 50; i++) {
			if((begin + i) >= userAgent.length() )
				break;
			char value = userAgent.charAt(begin + i);
			if (value == '/' || value == ';' || value == ' ') {
				end = begin + i;
				break;
			}
			if (begin + i == length - 1) {
				end = length;
				break;
			}
		}
		return userAgent.substring(begin + 1, end);
	}
	
	/**
	 * 通过版本号获取主版本号
	 * @param version
	 * @return
	 */
	public static  String getMainVersionFromVersion(String version) {
		if(StringUtil.isEmpty(version))
			return "";
		int index = version.indexOf(".");
		if(index < 0 ) 
			return "";
		return version.substring(0 , index);
	}
	
}
