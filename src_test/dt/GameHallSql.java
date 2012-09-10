package dt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.onedear.util.FileUtils;

/**
 * <br>==========================
 * <br> @company ：优视科技
 * <br> @author  ：邱志明(onedear)
 * <br> @version ：1.0 
 * <br> @date    : 2012-8-29上午10:31:45
 * <br>==========================
 */
public class GameHallSql {
	
	public static void main(String[] args) throws IOException {
//		giftLvMoreThan7();
		gift50000Coin();
	}
	
		
	public static void gift50000Coin() throws IOException {
		List<String> list = FileUtils.getContent("C:\\Documents and Settings\\Administrator\\桌面\\新建 文本文档.txt");
		List<Long> uidList = new ArrayList<Long>();
		for (String s : list) {
			String[] ss = s.split(" ");
			if (ss.length != 2)
				continue;
			uidList.add(Long.parseLong(ss[1]));
		}
		String result = gift(uidList, 50000);
		System.out.println(result);
	}
	
	public static void giftLvMoreThan7() throws IOException {
		/*
		 * use dt_data_server_15;
update `dt_user` set coin = coin + 3088 where uid = 500694;
INSERT INTO `dt_user_coin_update`(uid, game_id, num, real_change_num, comment, updateTime, type, coin, room_id) VALUES ('500694', null, '3088', '3088', '论坛活动奖励', '1345514722775', '12', '0', null);
		 * 
		 * 
		 */
		
		List<String> list = FileUtils.getContent("C:\\Documents and Settings\\Administrator\\桌面\\24号晚上00点升级到7级以上的用户 - 副本.txt");
		List<Long> uidList = new ArrayList<Long>();
		for (String s : list) {
			String[] ss = s.split(" ");
			if (ss.length != 3)
				continue;
			uidList.add(Long.parseLong(ss[1]));
		}
		String result = gift(uidList, 3088);
		System.out.println(result);
	}
	
	public static String gift(List<Long> uidList, int coin) {
		StringBuffer sql = new StringBuffer();
		for (Long numbers : uidList) {
			long uid = numbers;
			long mod = uid % 20 + 1;
			sql.append("use dt_data_server_" + mod + ";").append("\r\n");
			sql.append("update `dt_user` set coin = coin + "+ coin +" where uid = " + uid + ";").append("\r\n");
			sql.append("INSERT INTO `dt_user_coin_update`(uid, game_id, num, real_change_num, comment, updateTime, type, coin, room_id) VALUES ('" + uid + "', null, '"+coin+"', '"+coin+"', '论坛活动奖励', '"+ System.currentTimeMillis() +"', '12', '0', null);").append("\r\n");
			sql.append("\r\n");
		}
		return sql.toString();
	}
	
}
