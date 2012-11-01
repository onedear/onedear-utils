package com.onedear.util.reflect;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import com.onedear.exceptions.PackageNotFoundException;


/**
 * <br>==========================
 * <br> @company ：优视科技
 * <br> @author  ：邱志明(onedear)
 * <br> @version ：1.0 
 * <br> @date    : 2012-10-19上午11:44:19
 * <br>==========================
 */
public class ReflectUtil {
	public static Object reflectStaticMethod(String clazzName, String methodName, Object... params) 
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Class clazz = Class.forName(clazzName);
//		Method method = clazz.getDeclaredMethod(methodName);
		Class[] classes = null;
		if (params != null && params.length > 0) {
			classes = new Class[params.length];
			for (int i = 0; i < params.length; i++) {
				classes[i] = params[i].getClass();
			}
		}
		Method method = clazz.getMethod(methodName, classes);
		method.setAccessible(true);
		Object obj = method.invoke(null, params);
		return obj;
	}
	
	
	public static List<Class> findPackageClasses(String packageStr) throws ClassNotFoundException {
		String folderStr = ReflectUtil.class.getResource("/").getPath() 
				+ File.separator + packageStr.replace(".", File.separator);
		File packageFolder = new File(folderStr);
		if (!packageFolder.isDirectory()) {
			throw new PackageNotFoundException(folderStr);
		}
		File[] files = packageFolder.listFiles();
		if (files == null || files.length < 1) {
			return null;
		}
		List<Class> list = new ArrayList<Class>(files.length);
		for (File file : files) {
			if (file.isDirectory())
				continue;
			String fileName = file.getName();
			String className = packageStr + "." + fileName.substring(0, fileName.lastIndexOf("."));
			Class clazz = null;
			clazz = Class.forName(className);
			list.add(clazz);
		}
		return list;
	}
	
	
	
		
	
}



