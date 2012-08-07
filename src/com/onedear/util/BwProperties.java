package com.onedear.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class BwProperties extends Properties {

	private static final long serialVersionUID = -6565138139913919011L;
	
	
	// 获取CLASSPATH路径
//	public static String classPath = BwProperties.class.getResource("/").getPath();

	/* 构造方法 */
	public BwProperties(String propertiesPath) throws Exception {
		try {
			InputStream is = new FileInputStream(propertiesPath);
			this.load(is);
		} catch (IOException e) {
			throw new Exception("读取属性文件错误 !", e);
		}
	}

	/**
	 * 方法说明：公有的静态方法,得到BwProperties对象
	 *
	 * @param propertiesPath
	 * @return
	 * @author fisher
	 */
	public static BwProperties getPro(String propertiesPath) {
		return init(propertiesPath);
	}



	/**
	 * 方法说明：初始化内部属性
	 * 
	 * @param propertiesPath
	 * @author fisher
	 */
	private static BwProperties init(String propertiesPath) {
		BwProperties bwPro = null;
			try {
				bwPro = new BwProperties(propertiesPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return bwPro;
	}
	
	
	public static Map<String , String> initToMap(String propertiesPath) {
		BwProperties bwPro = init(propertiesPath);
		Map<String , String > map = new HashMap<String , String>();
		Set<Object> keySet = bwPro.keySet();
		Iterator<Object> iterator = keySet.iterator();
		while(iterator.hasNext()) {
			String key = (String)iterator.next();
			map.put(key, bwPro.getProperty(key));
		}
		return map;
	}
	
	/**
	 * 指定编码获取properties文件中的属性值（解决中文乱码问题）
	 * 
	 * @param properties
	 *            java.util.Properties
	 * @param key
	 *            属性key
	 * @return
	 */
	public String getProperty(String key) {

		// 如果此时value是中文，则应该是乱码
		String value = super.getProperty(key);
		if (value == null)
			return null;

		// 编码转换，从ISO8859-1转向指定编码
		try {
			value = new String(value.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

}
