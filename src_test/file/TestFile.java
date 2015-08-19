package file;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.onedear.util.FileUtils;

/**
 * <br>==========================
 * <br> @company ：优视科技
 * <br> @author  ：邱志明(onedear)
 * <br> @version ：1.0 
 * <br> @date    : 2012-9-10上午11:02:02
 * <br>==========================
 */
public class TestFile {
	
	@Test
	public void test() throws IOException, ClassNotFoundException, ParseException {
		List<String> times = FileUtils.getContent("/Users/onedear/file/java/workspace/onedear-utils/time.txt");
		long now = System.currentTimeMillis();
		Calendar c1 = GregorianCalendar.getInstance();
		c1.setTimeInMillis(now);
		for (String time : times) {
			String[] timeArr = time.split(":");
			c1.add(Calendar.HOUR, Integer.parseInt(timeArr[0]));
			c1.add(Calendar.MINUTE, Integer.parseInt(timeArr[1]));
			c1.add(Calendar.SECOND, Integer.parseInt(timeArr[2]));
		}
		System.out.println(c1);
		System.out.println(new Date(c1.getTimeInMillis()));
		System.out.println((c1.getTimeInMillis() - now) / (3600 * 1000));
	
		times = FileUtils.getContent("/Users/onedear/file/java/workspace/onedear-utils/time2.txt");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<Integer, Integer> timeMap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> dayMap = new HashMap<Integer, Integer>();
		for (String time : times) {
			Date d = df.parse(time);
			int hour = d.getHours();
			int day = d.getDate();
			System.out.println(day);
			Integer hourTimes = timeMap.get(hour);
			if (hourTimes == null) {
				timeMap.put(hour, 1);
			} else {
				timeMap.put(hour, hourTimes + 1);
			}
			
			Integer dayTimes = dayMap.get(day);
			if (dayTimes == null) {
				dayMap.put(day, 1);
			} else {
				dayMap.put(day, dayTimes + 1);
			}
		}
		System.out.println("我1月份打给最牵挂的人的电话次数总数是" + times.size()+"次, 其中每个小时的分布图如下");
		for (int hour : timeMap.keySet()) {
			System.out.println("小时:" + hour + " 通话了" + timeMap.get(hour) + "次");
		}
		System.out.println("------");
		for (int day : dayMap.keySet()) {
			System.out.println("" + day + "号 通话了" + dayMap.get(day) + "次");
		}
//		System.out.println(timeMap);
	}
}








