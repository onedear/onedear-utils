package file;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
	public void test() throws IOException, ClassNotFoundException {
		List<String> jsAscii = FileUtils.getContent("C:\\Documents and Settings\\Administrator\\桌面\\js_ascii.txt");
		List<String> newJsAscii = new ArrayList<String>();
		for (String ascii : jsAscii) {
			if (ascii.indexOf("-> ") < 1)
				continue;
			int start = ascii.indexOf("-> ") + 3;
			newJsAscii.add(ascii.substring(start));
		}
		
		char c = 0;
		for (int i = 0; i < 128; i++) {
			String encodeChar = URLEncoder.encode(String.valueOf(c), "utf-8");
//			System.out.println(c + " ---> " + encodeChar);
			if (!encodeChar.equals(newJsAscii.get(i))) {
				System.out.println(c + " java: " + encodeChar + ", js: " + newJsAscii.get(i));
			}
			c += 1;
		}
	}
}








