import java.io.IOException;

import com.onedear.util.FileUtils;


public class TestFileUtils {
	public static void main(String[] args) throws IOException {
		System.out.println(FileUtils.getContent("1.txt", false));
	}
}
