package date;

import java.text.ParseException;
import java.util.Date;

import com.onedear.util.DateUtil;

public class TestDate {
	public static void main(String[] args) throws ParseException {
		String dateStr = "2012-11-2 00:00:00.000";
		Date date = DateUtil.StringToDate(dateStr, "yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println(date.getTime());
		
		dateStr = "2012-11-9 23:59:59.000";
		date = DateUtil.StringToDate(dateStr, "yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println(date.getTime());
		
	}
}
