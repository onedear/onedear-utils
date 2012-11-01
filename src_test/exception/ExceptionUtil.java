package exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <br>==========================
 * <br> @company ：优视科技
 * <br> @author  ：邱志明(onedear)
 * <br> @version ：1.0 
 * <br> @date    : 2012-10-23上午9:34:28
 * <br>==========================
 */
public class ExceptionUtil {
	
	public static String exceptionToString(Throwable t) {
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
	
	public static void xxx() {
		throw new NullPointerException();
	}
	
}
