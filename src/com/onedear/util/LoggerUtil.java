package com.onedear.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*
* <br>==========================
* <br> 开发：onedear
* <br> 版本：1.0
* <br> 创建时间：2015年2月12日 下午2:46:47
* <br>==========================
*/
public class LoggerUtil {
	
//	access日志
	private static Logger gameLoginAccess = LoggerFactory.getLogger("game.access");

//	/**
//	 * 这个专门用于在gate的时候调用
//	 * @param uid
//	 * @param roleId
//	 * @param action
//	 */
//	public static void logAccess(long uid, int roleId, String action) {
//		String dateStr = DateUtil.getyyyyMMddHHmmss();
//		String channel = "ch";
//		if (gameLoginAccess.isInfoEnabled()) {
//			gameLoginAccess.info("t={}`ch={}`serverId={}`roleId={}`uid={}`level={}`a={}`",
//					new Object[]{dateStr, channel, RemoteConfig.ServerID, roleId, uid, 0, action});
//		}
//	}
//	
//	/**
//	 * channel里面调用
//	 * @param role
//	 * @param action
//	 */
//	public static void logAccess(GameUserRole role, String action) {
//		String dateStr = DateUtil.getyyyyMMddHHmmss();
//		String channel = "ch";
//		if (gameLoginAccess.isInfoEnabled()) {
//			gameLoginAccess.info("t={}`ch={}`serverId={}`roleId={}`uid={}`level={}`a={}`",
//					new Object[]{dateStr, channel, RemoteConfig.ServerID, role.roleId, role.uid, role.level, action});
//		}
//	}
//	
//	/**
//	 * channel里面调用
//	 * @param role
//	 * @param action
//	 */
//	public static void logLevelUp(GameUserRole role, int lv) {
//		String dateStr = DateUtil.getyyyyMMddHHmmss();
//		String action = "等级升级";
//		String channel = "ch";
//		if (gameLoginAccess.isInfoEnabled()) {
//			gameLoginAccess.info("t={}`ch={}`serverId={}`roleId={}`uid={}`level={}`a={}`",
//					new Object[]{dateStr, channel, RemoteConfig.ServerID, role.roleId, role.uid, lv, action});
//		}
//	}
	
	public static String getTraceLog() {
		return getTraceLog(10);
	}
	
	/**
	 * 
	 * @param length  最高获取几层?
	 * @return
	 */
	public static String getTraceLog(int length) {
		StringBuffer sb = new StringBuffer();
		StackTraceElement[]sets = Thread.currentThread().getStackTrace();
		if (sets.length < length)
			length = sets.length;
		for (int i = 3; i < length; i++) {
			StackTraceElement set = sets[i];
			sb.append(set.getClassName().substring(set.getClassName().lastIndexOf(".") + 1)).append("[").append(set.getLineNumber()).append("],");
		}
//		sb.append("\r\n---------");
//		for (int i = 1; i < sets.length; i++) {
//			StackTraceElement set = sets[i];
//			sb.append(set.getClassName().substring(set.getClassName().lastIndexOf(".") + 1)).append("[").append(set.getLineNumber()).append("]\r\n");
//		}
		return sb.toString();
	}

}
