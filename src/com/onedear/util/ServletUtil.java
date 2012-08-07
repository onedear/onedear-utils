package com.onedear.util;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 * @author onedear
 * @data:2010-11-8 下午07:37:13
 */
public class ServletUtil {
	/**
	 * 参数是整个request，因为可以自由获取header相应的值
	 * @param req
	 * @return
	 */
	public static boolean  isAjax(HttpServletRequest req) {
		String ajaxValue = req.getHeader("X-Requested-With");
		if(ajaxValue != null){
			return true; 
		}
		return false ; 
	}
	
	
	/**
	 * 注意：可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，
	 * 而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 * 如：
	 * X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 192.168.1.100
	 * 用户真实IP为： 192.168.1.110
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");      
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
            ip = request.getHeader("Proxy-Client-IP");      
        }      
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
            ip = request.getHeader("WL-Proxy-Client-IP");      
        }      
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
           ip = request.getRemoteAddr();      
       }      
       return ip;  
	}

}
