package com.onedear.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 关于读取文件的一些工具类
 * @author onedear
 *	2009-11-3 pm 2：36
 */
public class FileUtils {
	
	public static List<String> getContent(String path) throws IOException {
		File file = new File(path);
		return getContent(file);
	}
	
	public static List<String> getContent(File file) throws IOException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			List<String> list = new ArrayList<String>();
			String line = reader.readLine();
			while (line != null) {
				list.add(line); 
				line = reader.readLine();
			}
			return list;
		} finally {
			reader.close();
		}
	}
	
	/**
	 * 将content存进一个新文件
	 * @param path  为空则直接读取filename
	 * @param filename
	 * @param content
	 * @return
	 */
	public static boolean stringToFile(String path , String filename , String content ) {
		String filepath = null;
		if(path == null)
			filepath = filename; 
		else if(path.endsWith("/") || filename.startsWith("/"))
			filepath = path + filename;
		else 
			filepath = path + "/" + filename; 
		File file = new File(filepath);
//		file.delete();
		PrintWriter pw = null;
		try {
			if(!file.exists())
				file.createNewFile();
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"));
			pw.print(content);
			pw.flush();
			
		} catch(IOException e) {
			return false; 
		} finally {
			pw.close();
		}
		return true; 
	}
	
	/**
	 * list保存至指定文件
	 * @param path
	 * @param filename
	 * @param list
	 * @return
	 */
	public static boolean listToFile(String filename , List<String> list ) {
		File file = new File(filename);
		OutputStreamWriter osw = null;
		try {
			if(!file.exists())
				file.createNewFile();
			osw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			for(String content : list) {
				osw.write(content);
				osw.write("\n");
			}
			osw.flush();
		} catch(IOException e) {
			return false; 
		} finally {
			try {
				osw.close();
			} catch (Exception e) {
				
			}
		}
		return true; 
	}
	
	public static void saveObjToFile(String path , String fileName , Object obj) throws IOException {
		String filepath = null;
		if(path == null)
			filepath = fileName; 
		else if(path.endsWith("/"))
			filepath = path + fileName;
		else 
			filepath = path + "/" + fileName; 
		
		FileOutputStream fos = new FileOutputStream(filepath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        try {
        	oos.writeObject(obj);
        } finally {
        	oos.close();
        }
	}
	
	/**
	 * 
	 * 
	 * @param path
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object readObjFromFile(String path , String fileName) throws IOException, ClassNotFoundException {
		String filepath = null;
		if(path == null)
			filepath = fileName; 
		else if(path.endsWith("/"))
			filepath = path + fileName;
		else 
			filepath = path + "/" + fileName; 
		
		FileInputStream fis = new FileInputStream(filepath);
        ObjectInputStream ois = null;
        try {
        	ois = new ObjectInputStream(fis);
        	return ois.readObject();
        } finally {
        	ois.close();
        }
	}
	
	/**
	 * @param toFilepath
	 * @param files
	 * @return
	 * @throws IOException 
	 */
	public static boolean merge(String toFilepath, File[] files) throws IOException {
		File toFile = new File(toFilepath);
		if (toFile.isDirectory()) {
			throw new RuntimeException("神马, toFilepath参数竟然是一个文件夹? [{}]" + toFilepath);
		}
		if (files == null || files.length < 1) {
			throw new RuntimeException("文件列表为空,无法合并");
		}
		if (toFile.exists()) {
			toFile.delete();
			toFile.createNewFile();
		}
		for (File file : files) {
			
			List<String> sourceList = getContent(file);
			if (sourceList == null || sourceList.size() < 1) {
				continue;
			}
			
			OutputStreamWriter osw = null;
			try {
				osw = new OutputStreamWriter(new FileOutputStream(toFile, true), "UTF-8");
				for (String line : sourceList) {
					osw.write(line);
					osw.write("\r\n");
				}
				osw.flush();
			} finally {
				try {
					osw.close(); 
				} catch (Exception e) {}
			} 

		}
		return true;
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
//		Date date = new Date();
//		saveObjToFile("c:/" , "testObj" , date);
//		Object obj = readObjFromFile("c:/" , "testObj" );
//		Date dd = (Date)obj;
//		System.out.println(dd);
		stringToFile("/Users/onedear/Desktop", "name.txt", "('跟你ξ不熟↘')");
	}
	
}
