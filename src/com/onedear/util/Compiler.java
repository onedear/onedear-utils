package com.onedear.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * @author onedear
 *
 */
public class Compiler { 
	private static boolean hasFirstInit = false;
	public static String getClassCode (String initParam) {
		StringBuffer sb = new StringBuffer();
		sb.append("public class ETEvaler {")
			.append("public Object eval() {")
			.append(initParam)
			.append("}")
			.append("}");
		return sb.toString() ; 
	}
	
	public static Object eval(String sourceCode) 
			throws SecurityException, NoSuchMethodException, IOException, 
			ClassNotFoundException, IllegalArgumentException, 
			IllegalAccessException, InvocationTargetException, InstantiationException {
		URLClassLoader classLoader = new URLClassLoader(
				new URL[] { new File(System.getProperty("java.io.tmpdir")).toURI().toURL() });
		Class clazz = null ; 
		boolean hasExists = true ; 
		 try{ 
			 clazz = classLoader.loadClass("ETEvaler");
		 } catch (ClassNotFoundException e) {
			 //说明类未初始化过，需要初始化
			 hasExists = false ; 
		 }
		 if(!hasExists || !hasFirstInit) {
			 hasFirstInit = true;
			 String classCode = getClassCode(sourceCode);
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
	        if(compiler == null)
	        	throw new IllegalArgumentException("系统java编译器无法找到，请确认类路径中已经包含tools.jar（注：JDK 6中默认自带，JRE 6中默认不带)");
	        DiagnosticCollector diagnostics = new DiagnosticCollector();
	        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
	        String fileName = "ETEvaler.java";
	        File file = new File(System.getProperty("java.io.tmpdir"), fileName);
	        PrintWriter pw = new PrintWriter(file);
	        pw.println(classCode);
	        pw.close();
	        Iterable compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays.asList(file
	                        .getAbsolutePath()));
	        JavaCompiler.CompilationTask task = compiler.getTask(null,
	                fileManager, diagnostics, null, null, compilationUnits);
	        boolean success = task.call();
	        fileManager.close();
		 }
		//必须重新加载,否则当重新eval时无法立刻生效
		classLoader = new URLClassLoader(
					new URL[] { new File(System.getProperty("java.io.tmpdir")).toURI().toURL() });
        clazz = classLoader.loadClass("ETEvaler");
        Method method = clazz.getDeclaredMethod("eval");
        Object value = method.invoke(clazz.newInstance() );
        return value ; 
		
	}
	
	public static void main(String[] args) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IOException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Compiler.eval("System.out.println(\"e1111112222222\");return \"returnValue\";");
		
		String value = (String)Compiler.eval("System.out.println(\"e1111112222222\");");
		System.out.println(value);
	}
}
