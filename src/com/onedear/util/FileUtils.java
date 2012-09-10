package com.onedear.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	/**
	 * 数据文件的path，建议完整的路径
	 * isRoot，是否从根目录找起，true从类的当前目录找起
	 * @param path
	 * @param isRoot
	 * @return
	 * @throws IOException 
	 */
	public static String getContent(String path , boolean isRoot) throws IOException{
		File file = new File(path);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			StringBuffer content = new StringBuffer(); 
			String line = reader.readLine();
			while (line != null) {
				content.append(line); 
				line = reader.readLine();
			}
			return content.toString();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} 
	
	public static List<String> getContent(String path) throws IOException {
		File file = new File(path);
		return getContent(file);
	}
	
	public static List<String> getContent(File file) throws IOException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
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
	 * @param path
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
		file.delete();
		PrintWriter pw = null;
		try {
			if(!file.exists())
				file.createNewFile();
			pw = new PrintWriter(file);
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
		PrintWriter pw = null;
		try {
			if(!file.exists())
				file.createNewFile();
			pw = new PrintWriter(file);
			for(String content : list)
				pw.println(content);
			pw.flush();
		} catch(IOException e) {
			return false; 
		} finally {
			pw.close();
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

        oos.writeObject(obj);
        oos.close();
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
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Date date = new Date();
		saveObjToFile("c:/" , "testObj" , date);
//		Object obj = readObjFromFile("c:/" , "testObj" );
//		Date dd = (Date)obj;
//		System.out.println(dd);
	}
	
}
