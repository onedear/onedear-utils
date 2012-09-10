package date;

import java.text.ParseException;
import java.util.Date;

import com.onedear.util.DateUtil;

public class TestDate {
	public static void main(String[] args) throws ParseException {
		String dateStr = "2012-08-24 00:00:00.000";
		Date date = DateUtil.StringToDate(dateStr, "yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println(date.getTime());
//		String s = ((Long)(DateUtil.StringToDate(dateStr, "yyyy-MM-dd HH:mm:ss.SSS")).getTime()).toString();
//		System.out.println(s);
//		
////													yyyy-MM-dd HH:mm:ss.SSS 	1321513873333 1321513873333
//		System.out.println(date.getTime());
//		System.out.println(DateUtil.DateToString(date));
////		
//		Date date2 = new Date(1321548870562L);
//		System.out.println(date2.getTime());
//		System.out.println(DateUtil.DateToString(date2));
	}
}
