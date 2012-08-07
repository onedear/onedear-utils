package com.onedear.util;

import java.util.UUID;


/**
 * 普通的工具类
 * @author onedear
 *
 */
public class NormalUtils {
	/**
	 * 获取类的绝对路径 
	 * @param cls 
	 * @return
	 */
	public  static <T extends Object> String getRelPath(T c){
		Class<? extends Object> t = c.getClass();
		String path = t.getClass().getResource("").getPath();
		return path ; 
	}
	
	public <T extends Object> T sss(T t, Integer i) {
        Class<? extends Object> c = t.getClass();
        // do....
        return null;
    }
	
	
	public String getClassPath(){
		// 获取CLASSPATH路径
		return  NormalUtils.class.getResource("/").getPath();
	}
	
	public String getProjectPath() {
		return System.getProperty("user.dir");
	}
	
	/**
	 * 不会重复的32位uuid字符串，可用于做数据库的主键保存
	 * @return
	 */
	public String getUUID(){
		String id = UUID.randomUUID().toString() ;
    	id = id.replace("-", "");
    	return id ;
	}
	
	public static void main(String[] args) {
		NormalUtils n = new NormalUtils();
		System.out.println(n.getClassPath());
	}
	
}
