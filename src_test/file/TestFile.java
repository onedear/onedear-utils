package file;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.onedear.util.FileUtils;
import com.onedear.util.reflect.ReflectUtil;

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
		List<Class> list = ReflectUtil.findPackageClasses("com.onedear.util");
		System.out.println(list);
		
	}
}








