package com.onedear.util.net;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * <br>==========================
 * <br> @intro   :
 * <br> @author  ：onedear
 * <br> @version ：1.0 
 * <br> @date    : 2012-12-10下午8:08:11
 * <br>==========================
 */
public class FtpUtil {
	public static boolean downloadFile(String host, int port, String remotePath, String username, 
			String password, String toPath) {
		FTPClient ftp = new FTPClient();
		try {
			int reply = 0;
			ftp.connect(host, port);
			reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply))
            {
                ftp.disconnect();
                System.err.println("FTP server refused connection.");
                System.exit(1);
            }
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		String host = "10.34.132.52";
		int port = 9080;
		String remotePath = "gamehall/gamehall-access_2012120104.log";
		String username = "admin";
		String password = "password";
		String toPath = "C:\\Documents and Settings\\Administrator\\桌面\\ftpdownload";
		boolean success = downloadFile(host, port, remotePath, username, password, toPath);
		System.out.println(success);
	}
}
