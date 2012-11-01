package com.onedear.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
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
	
	/**
	 * 获取下一个时间点与当前的毫秒
	 * @param hour
	 * @return
	 */
	public static long getNextHourTime(int hour) {
		Calendar c1 = GregorianCalendar.getInstance();
		c1.setTimeInMillis(System.currentTimeMillis());
		int currentHour = c1.get(Calendar.HOUR_OF_DAY);
		if (currentHour >= hour) {
			c1.set(Calendar.HOUR_OF_DAY, 0);
			c1.set(Calendar.MINUTE, 0);
			c1.set(Calendar.SECOND, 0);
			c1.set(Calendar.MILLISECOND, 0);
			c1.add(Calendar.DAY_OF_YEAR, 1);
			c1.add(Calendar.HOUR_OF_DAY, hour);
		} else {
			c1.set(Calendar.HOUR_OF_DAY, hour);
			c1.set(Calendar.MINUTE, 0);
			c1.set(Calendar.SECOND, 0);
			c1.set(Calendar.MILLISECOND, 0);
		}
		return c1.getTimeInMillis();
	}
	
	/**
	 * 判断两个日期是否同一天
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean isSameDay(long time1, long time2) {
		Calendar c1 = GregorianCalendar.getInstance();
		Calendar c2 = GregorianCalendar.getInstance();
		c1.setTimeInMillis(time1);
		c2.setTimeInMillis(time2);
		if(c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR))
			return true;
		return false;
	}
	
	/**
	 * 判断日志是否昨天
	 * @param time
	 * @return
	 */
	public static boolean isYesterday(long time) {
		long distance = dayDistance(time);
		if (distance == -1) 
			return true;
		return false;
	}
	
	/**
	 * 查看距离今天有多少天,负数是过去,正数是未来
	 * @param time
	 * @return
	 */
	public static long dayDistance(long time) {
		Calendar c1 = getDayCalendar(time);
		Calendar c2 = getDayCalendar(System.currentTimeMillis());
		long dayMillis = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
		return (c1.getTimeInMillis() - c2.getTimeInMillis()) / dayMillis;
	}
	
	private static Calendar getDayCalendar(long millis) {
		Calendar c1 = GregorianCalendar.getInstance();
		c1.setTimeInMillis(millis);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);
		return c1;
	} 
	
	public static void main(String[] args) {
		System.out.println(DateToString(new Date() , 3));
	}
}
