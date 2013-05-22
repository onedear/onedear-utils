package com.onedear.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <br>==========================
 * <br> @intro   :
 * <br> @author  ：onedear
 * <br> @version ：1.0 
 * <br> @date    : 2012-11-27下午7:46:42
 * <br>==========================
 */
public class ExceptionUtil {
	
	public static String getExceptionStack(Throwable e) {
		StringWriter sw = new StringWriter();
		try {
			e.printStackTrace(new PrintWriter(sw));
			return sw.toString();
		} finally {
			try {
				sw.close();
			} catch (IOException e1) {
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			"".substring(1); 
		} catch (Exception e) {
			System.out.println(getExceptionStack(e));
		}
	}
	
}
