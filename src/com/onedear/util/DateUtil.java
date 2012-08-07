package com.onedear.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * 关于时间的一点工具类
 * @author onedear
 *
 */
public class DateUtil {
	private final static String format1 = "yyyy-MM-dd";
	private final static String format2 = "yyyy-MM-dd HH:mm:ss";
	private final static String format3 = "yyyy-MM-dd HH:mm";
	/**
	 * 将时间转换成字符串，按照指定格式
	 * @param date 为空的话，默认去当前时间
	 * @param i 取值为1，2，3，分别对应
	 *  1 =  "yyyy-MM-dd";
	    2 =  "yyyy-MM-dd HH:mm:ss";
	    3 = "yyyy-MM-dd HH:mm";
	 * @return
	 */
	public static String DateToString(Date date , int i){
		String format = null ; 
		if(i == 1)
			format = format1 ;
		else if(i == 2) 
			format = format2 ; 
		else if(i == 3)
			format = format3;
		else format = format1;
		DateFormat sdf = new SimpleDateFormat(format);
		if(date == null ) {
			date = new Date()  ;
		}
		return sdf.format(date);
	}
	/**
	 * 
	 * @param date 为空的话，默认去当前时间
	 * @param format 自定义yyyy-MM-dd HH:mm:ss这种格式
	 * @return
	 */
	public static String DateToString(Date date , String format){
		DateFormat sdf = new SimpleDateFormat(format);
		if(date == null ) {
			date = new Date()  ;
		}
		return sdf.format(date);
	}
	/**
	 * 获取当前时间，格式是 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String DateToString(Date date){
		return DateToString(date, 2);
	}
	
	public static Date StringToDate(String str , String format) throws ParseException {
		DateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(str);
	}
	
	public static Date nextDate(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, 1);
		return c.getTime();
	}
	
	public static void main(String[] args) {
		System.out.println(DateToString(new Date() , 3));
	}
}
