package com.onedear.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author onedear
 * @data:2010-11-8 下午07:50:42
 */
public class RegexUtil {
	
	/**
	 * 尚未验证的方法
	 * @param pattern
	 * @param html
	 * @param tag
	 * @param script
	 * @return
	 */
	public static String replaces(Pattern pattern,String html,String tag,String script){
		StringBuffer sb = new StringBuffer();
		String replacement = "";
		
		Matcher matcher =pattern.matcher(html);
		
		int findedTime=0;
		while(matcher.find()){
			findedTime+=1;
			matcher.appendReplacement(sb,replacement);//结果是My name is Smith. James Smith
		}
		//System.out.println(findedTime);
		if(findedTime>0){
			matcher.appendTail(sb);
			String ss=new String(sb);
			return ss;	 //有找到 替换了
		}else{
			return html;
		}
	}
}
