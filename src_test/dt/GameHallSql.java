package dt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//		gift50000Coin();
//		queryUcidFromUid();
//		xiaoHei();
//		zhuanPanGuoQi();
//		getUserPlayNum();
//		getUserCoinFromUserHasLogin();
		appendPuidToFile();
	}
	
	public static void queryUcidFromUid() throws IOException {
		List<String> list = FileUtils.getContent("C:\\Documents and Settings\\Administrator\\桌面\\游客信息.txt");
		List<String> uidList = new ArrayList<String>();
		for (String s : list) {
			uidList.add(s.replace("游客", ""));
		}
		StringBuilder sb = new StringBuilder();
		for(String uid : uidList) {
			long db = Long.parseLong(uid) % 20 + 1;
			sb.append("select uid, platform_uid from dt_data_server_"+db+".dt_uid_map where uid = " + uid);
			sb.append("\r\n");
			sb.append("union").append("\r\n");
		}
		System.out.println(sb.toString());
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
	
	public static void xiaoHei() throws IOException {
//		List<String> list = FileUtils.getContent("C:\\Documents and Settings\\Administrator\\桌面\\小黑欢乐豆赠送\\已赠送的欢乐豆.txt");
//		List<String> uidList = new ArrayList<String>();
//		for (int i = 0; i < list.size(); i += 2) {
//			String s = list.get(i);
//			int beginIndex = s.indexOf("\"uid\":") + 6;
//			int endIndex = s.indexOf(",\"goodsId\"");
//			String uid = s.substring(beginIndex, endIndex);
//			uidList.add(uid);
//		}
//		System.out.println(uidToUcid(uidList));
		
		List<String> uidUcidList = FileUtils.getContent("C:\\Documents and Settings\\Administrator\\桌面\\小黑欢乐豆赠送\\已赠送的uid.txt");
		Map<Long, Long> uidUcidMap = new HashMap<Long, Long>();
		for(String uidUcid : uidUcidList) {
			String[] ss = uidUcid.split("\t");
			long uid = Long.parseLong(ss[0]);
			long ucid = Long.parseLong(ss[1]);
			uidUcidMap.put(uid, ucid);
		}
		
		List<String> list = FileUtils.getContent("C:\\Documents and Settings\\Administrator\\桌面\\小黑欢乐豆赠送\\已赠送的欢乐豆.txt");
		List<UidGift> hasGiftList = new ArrayList<UidGift>();
		for (int i = 0; i < list.size(); i += 2) {
			String s = list.get(i);
			int beginIndex = s.indexOf("\"uid\":") + 6;
			int endIndex = s.indexOf(",\"goodsId\"");
			String uid = s.substring(beginIndex, endIndex);
			beginIndex = s.indexOf("\"goodsId\":") + 10;
			endIndex = s.indexOf(",\"num\"");
			String goodsId = s.substring(beginIndex, endIndex);
			beginIndex = s.indexOf(",\"num\":") + 7;
			endIndex = s.indexOf("}]");
			String num = s.substring(beginIndex, endIndex);
			UidGift ug = new UidGift();
			ug.goodsId = Long.parseLong(goodsId);
			ug.num = Integer.parseInt(num);
			ug.uid = Long.parseLong(uid);
			ug.ucid = uidUcidMap.get(ug.uid);
//			System.out.println(ug);
			hasGiftList.add(ug);
		}
		
		List<String> planList = FileUtils.getContent("C:\\Documents and Settings\\Administrator\\桌面\\小黑欢乐豆赠送\\应该赠送的欢乐豆.txt");
		
		List<UidGift> leftGiftList = new ArrayList<UidGift>();
		for (String plan : planList) {
			String[] ss = plan.split("\t");
			long ucid = Long.parseLong(ss[0]);
			long goodsId = Long.parseLong(ss[1]);
			int num = Integer.parseInt(ss[2]);
			
			UidGift mustUg = new UidGift();
			mustUg.goodsId = goodsId;
			mustUg.num = num;
			mustUg.ucid = ucid;
			
			boolean gift = false;
			for (UidGift ug : hasGiftList) {
				if (ucid == ug.ucid && goodsId == ug.goodsId && num == ug.num) {
					gift = true;
					hasGiftList.remove(ug);
					break;
				}
			}
			if (!gift) {
				leftGiftList.add(mustUg);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (UidGift ug : leftGiftList) {
			sb.append(ug.ucid).append("\t").append(ug.goodsId).append("\t").append(ug.num).append("\r\n");
		}
		System.out.println(sb.toString());
		System.out.println();
		
		System.out.println("应该赠送"+ planList.size() +"个用户");
		System.out.println("还剩"+ leftGiftList.size() +"个用户需要赠送");
		
//		System.out.println(uidToUcid(uidList));
	}
	
	public static void zhuanPanGuoQi() throws IOException {
		StringBuilder sb = new StringBuilder();
//		查询uid找出ucid
		List<String> leftList = FileUtils.getContent("C:\\Documents and Settings\\Administrator\\桌面\\官网转盘信息\\转盘剩余次数.txt");
		for (int i = 0; i < leftList.size(); i++) {
			String s = leftList.get(i);
			String[] ss = s.split("\t");
			long uid = Long.parseLong(ss[0]);
			int num = Integer.parseInt(ss[1]);
			int gift = num * 288;
			long db = uid % 20 + 1;
			sb.append("INSERT INTO dt_data_server_" + db + ".dt_user_coin_update(uid, game_id, num, real_change_num, comment, updateTime, type, coin, room_id) VALUES ('"+uid+"', null, '" +gift+ "', '" +gift+ "', '其他活动奖励', '"+System.currentTimeMillis()+"', '14', '0', null);");
			sb.append("\r\n");
			sb.append("update dt_data_server_" + db + ".dt_user set coin = coin + "+ gift +" where uid = " + uid + ";");
			sb.append("\r\n");
			sb.append("\r\n");
		}
		
		sb.append("\r\n").append("\r\n").append("\r\n");
//		INSERT INTO `dt_user_coin_update`(uid, game_id, num, real_change_num, comment, updateTime, type, coin, room_id) VALUES ('534421', null, '50000', '50000', '论坛活动奖励', '1346736644968', '12', '0', null);
		
		for (int i = 1; i < 21; i++) {
			sb.append("update dt_data_server_" + i + ".dt_activity_turntable_coupon set count = 0;").append("\r\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
	public static void getUserPlayNum() throws IOException {
		StringBuilder sb = new StringBuilder();
		List<String> leftList = FileUtils.getContent("C:\\Documents and Settings\\Administrator\\桌面\\用户信息\\25日已登录过的用户.txt");
		sb.append("select uid, play_num from zc_play_times where uid in (");
		for (int i = 0; i < leftList.size(); i++) {
			String s = leftList.get(i);
			String[] ss = s.split("\t");
			long uid = Long.parseLong(ss[0]);
			sb.append(uid).append(",");
		}
		sb.append(");");
		System.out.println(sb.toString());
	}
	
	public static void getUserCoinFromUserHasLogin() throws IOException {
		StringBuilder sb = new StringBuilder();
		List<String> leftList = FileUtils.getContent("C:\\Documents and Settings\\Administrator\\桌面\\用户信息\\25日已登录过的用户.txt");
		List<UidGift> ugList = new ArrayList<UidGift>();
		for (int i = 0; i < leftList.size(); i++) {
			String s = leftList.get(i);
			String[] ss = s.split("\t");
			long uid = Long.parseLong(ss[0]);
			long puid = Long.parseLong(ss[1]);
			UidGift ug = new UidGift();
			ug.uid = uid;
			ug.ucid = puid;
			ugList.add(ug);
		}
		for (int i = 1; i <= 20; i++) {
			sb.append("select uid, coin from dt_data_server_" + i + ".dt_user where uid >= 500000 and uid in(");
			for (UidGift ug : ugList) {
				if ((ug.uid % 20)  != (i - 1))
					continue;
				sb.append(ug.uid).append(",");
			}
			sb.append(")").append("\r\n");
			sb.append("union").append("\r\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void appendPuidToFile() throws IOException {
		Map<Long, Long> uidMap = new HashMap<Long, Long>();
		List<String> uidList = FileUtils.getContent("C:\\Documents and Settings\\Administrator\\桌面\\用户信息\\25日已登录过的用户.txt");
		for (String uids : uidList) {
			String[] ss = uids.split("\t");
			long uid = Long.parseLong(ss[0]);
			long puid = Long.parseLong(ss[1]);
			uidMap.put(uid, puid);
		}
		String base = "C:\\Documents and Settings\\Administrator\\桌面\\用户信息";
		String[] fileNames = new String[]{
				"ddz.txt"
				,"zc.txt"
				,"dzpk.txt"
				,"dn.txt"
				,"用户欢乐豆数.txt"
		};
		
		for (String fileName : fileNames) {
			List<String> newContent = new ArrayList<String>();
			List<String> list = FileUtils.getContent(base + "\\" + fileName);
			for (String line : list) {
				String[] ss = line.split("\t");
				long uid = Long.parseLong(ss[0]);
				long puid = uidMap.get(uid);
				line += "\t"+ puid;
				newContent.add(line);
			}
			FileUtils.listToFile(base + "\\___" + fileName, newContent);
		}
	}
	
	public static String uidToUcid(List<String> uidList) throws IOException {
		StringBuilder sb = new StringBuilder();
		for(String uid : uidList) {
			long db = Long.parseLong(uid) % 20 + 1;
			sb.append("select uid, platform_uid from dt_data_server_"+db+".dt_uid_map where uid = " + uid);
			sb.append("\r\n");
			sb.append("union").append("\r\n");
		}
		return sb.toString();
	}
	
	
	
}

class UidGift {
	public long uid;
	
	public long ucid;
	
	public int num;
	
	public long goodsId;
	
	public String toString() {
		return "uid : " + uid + ", ucid :" + ucid + ", num :" + num + ", goodsId :" + goodsId; 
	}
}



