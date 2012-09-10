package file;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	public static Set beanSet = new HashSet();
	public static Set accessSet = new HashSet();
	
	@Test
	public void test() throws IOException {
		testAccess();
		
		File file = new File("C:\\Documents and Settings\\Administrator\\桌面\\新建文件夹");
		if (!file.isDirectory()) {
			throw new RuntimeException("is not a directory");
		}
		File[] fs = file.listFiles();
		for (File f : fs) {
			List<String> contentList = FileUtils.getContent(f);
			for (String st : contentList) {
				if (st.indexOf("每日登陆赠送") > 0) {
					String uid = getUid(st);
					beanSet.add(uid);
				}
			}
		}
		System.out.println(beanSet.size());
	}
	
//	@Test
	public void testAccess() throws IOException {
		File file = new File("C:\\Documents and Settings\\Administrator\\桌面\\access-log\\1");
		if (!file.isDirectory()) {
			throw new RuntimeException("is not a directory");
		}
		File[] fs = file.listFiles();
		for (File f : fs) {
			List<String> contentList = FileUtils.getContent(f);
			for (String st : contentList) {
				String uid = getUid(st);
				System.out.println(uid);
				accessSet.add(uid);
			}
		}
		
		
		file = new File("C:\\Documents and Settings\\Administrator\\桌面\\access-log\\2");
		if (!file.isDirectory()) {
			throw new RuntimeException("is not a directory");
		}
		fs = file.listFiles();
		for (File f : fs) {
			List<String> contentList = FileUtils.getContent(f);
			for (String st : contentList) {
				String uid = getUid(st);
				System.out.println(uid);
				accessSet.add(uid);
			}
		}
		
		System.out.println(accessSet.size());
	}
	
	public String getUid(String st) {
		int start = st.indexOf("uid=") + 4;
		int end = start + st.substring(start).indexOf("`");
		String uid = st.substring(start, end);
		return uid;
	}
}








