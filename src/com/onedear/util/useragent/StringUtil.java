/*
 * Copyright Botwave.com All right reserved.
 */
package com.onedear.util.useragent;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public final class StringUtil {

	public static List<String> split(String str, String delim) {
		
		if(str == null || str.length() < 1)
			return new ArrayList<String>(0);
		
		if(delim == null || delim.length() < 1) {
			List<String> whole = new ArrayList<String>(1);
			whole.add(str);
			return whole;
		}
		
		List<String> tokens = new ArrayList<String>();
		
		StringTokenizer st = new StringTokenizer(str, delim);
		while(st.hasMoreTokens()) {
			tokens.add(st.nextToken());
		}
		return tokens;
	}
	
	public static boolean isEmpty(String str) {
		return str == null || trim(str).length() < 1 ? true : false;
	}
	
	
	/**
	 * 截段，如果长度截段，则阶段，否则返回原字符串
	 * @param str
	 * @param maxLength
	 * @return
	 */
	public static String subStringIfOverMaxLength(String str , int maxLength) {
		if(isEmpty(str))
			return null ;
		if(str.length()<maxLength)
			return str ;
		return str.substring(0, maxLength);
	}
	
	/**
	 * 去掉字符串两边的空格，包括半角与全角
	 * @param s
	 * @return
	 */
	public static String trim(String s) {
		if(s == null || s.length()<1)
			return s;
		int length = s.length();
		int start = 0 ;
		while(start < length && (s.charAt(start) == ' ' || s.charAt(start) == '　')) 
			start++;
		int end = length -1;
		while(end > 0 && (s.charAt(end)==' ' || s.charAt(end) == '　'))
			end --;
		if(end < 0 || (end +1) < start)
			return s;
		return s.substring(start,end + 1);
	}
	
	public static Integer toInteger(String str) {
		Integer i;
		try {
			i = Integer.valueOf(str);
		} catch(Exception e) {
			i = 0;
		}
		return i;
	}
	
	public static Long toLong(String str) {
		Long l;
		try {
			l = Long.valueOf(str);
		} catch(Exception e) {
			l = 0L;
		}
		return l;
	}
	
	public static Date toDate(String strTimespan) {
		Long l = toLong(strTimespan);
		return new Date(l);
	}
	
	public static String formatDollar(String format, String ... args) {

		int lenOfFormat = format.length();
		StringBuilder sb = new StringBuilder();
		
		int iDollar = 0;
		int lastIndex;
		
		for (int i = 0; true; i++) {

			lastIndex = iDollar;
			iDollar = format.indexOf('$', lastIndex);
			
			if(iDollar != -1) {
				sb.append(format.substring(lastIndex, iDollar));
				sb.append(args[i]);
				
				if(++iDollar == lenOfFormat)
					break;
			} else {
				sb.append(format.substring(lastIndex));
				break;
			}
		}
		return sb.toString();
	}
	
	public static String extractFirstDollar(String format, String target) {
		List<String> values = extractDollar(format, target);
		if(values != null && values.size() > 0)
			return values.get(0);
		return null;
	}
	public static List<String> extractDollar(String format, String target) {

		int iFormatCurr = 0;
		int iTargetCurr = 0;

		boolean isLastRoune = false;
		
		List<String> values = new ArrayList<String>();
		
		while(true) {
			
			int iDollar = format.indexOf('$', iFormatCurr);
			if(iDollar == -1) {
				return values;
			}
			
			int distance = iDollar - iFormatCurr;
			iFormatCurr = iDollar + 1;
			iTargetCurr += distance;
			
			int iNextDollar = format.indexOf('$', iFormatCurr);

			if(iNextDollar == -1) {
				iNextDollar = format.length();
				isLastRoune = true;
			}

			int iValueEnd;
			if(iFormatCurr < format.length()) {
				
				String rightBoundray = format.substring(iFormatCurr, iNextDollar);
				iValueEnd = target.indexOf(rightBoundray, iTargetCurr);
				
				if(iValueEnd == -1) {
					throw new RuntimeException("目标: [" + target + "] 的格式对不上: [" + format + "], 找不到左边界: [" + rightBoundray + "]" + ", VALUES: " + values);
				}
				
			} else {
				iValueEnd = target.length();
			}

			
			String value = target.substring(iTargetCurr, iValueEnd);
			
			values.add(value);
			
			if(isLastRoune)
				break;

			iTargetCurr += value.length();
		}
		
		return values;
	}
	

	public static byte[] getUTF8Bytes(String str) {
		try {
			return str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String concat(String... strs) {
		if(strs == null)
			return null ;
		StringBuffer sb = new StringBuffer();
		for(String s : strs) {
			if(s == null)
				continue;
			sb.append(s);
		}
		return sb.toString();
	} 
	
	public static void main(String[] args) {
		System.out.println("event:$:param:$:value - event:123:param:456:value - " + extractDollar("event:$:param:$:value", "event:123:param:456:value"));
		System.out.println("p:warning:rules - p:warning:rules - " + extractDollar("p:warning:rules", "p:warning:rules"));
		System.out.println("rule:$ - rule:kevin_cheng - " + extractDollar("rule:$", "rule:kevin_cheng"));
		System.out.println("$$:rule$$_ - kevin_chen:rules_ - " + extractDollar("$$:rule$$_", "kevin_chen:rules_"));
		System.out.println("$$:rule$$_$ - kevin_chen:rules_ - " + extractDollar("$$:rule$$_$", "kevin_chen:rules_"));
		System.out.println("event:$:param:$:value - event:123:param:456:value - " + extractDollar("event:$:param:$:value", "event:123:aram:456:value"));
	}
}
