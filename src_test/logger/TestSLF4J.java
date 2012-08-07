package logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSLF4J {
	
	private static Logger logger = LoggerFactory.getLogger(TestSLF4J.class);
	
	public static void main(String[] args) {
		logger.error("this is log");
		logger.info("{},{},{}" ,new Object[]{ "1", "2", "3"}); 
	}
}
