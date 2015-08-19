package com.onedear.util;import java.io.ByteArrayInputStream;import java.io.ByteArrayOutputStream;import java.io.IOException;import java.io.ObjectInputStream;import java.io.ObjectOutputStream;public class ByteUtil {		public static Object bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {		if(bytes == null) 			return null ;		ByteArrayInputStream bis = null ; 		ObjectInputStream ois = null ; 		try {			bis = new ByteArrayInputStream(bytes);			ois = new ObjectInputStream(bis);			Object obj = ois.readObject();			return obj ;		} finally {			try {				bis.close();				ois.close();			} catch (IOException e) {			}		}	}		public static byte[] objectToBytes(Object obj) throws IOException {		if(obj == null) 			return null ; 		ByteArrayOutputStream bos = null ; 		ObjectOutputStream oos = null ; 		try {			bos = new ByteArrayOutputStream();			oos = new ObjectOutputStream(bos);			oos.writeObject(obj);			byte[] bytes = bos.toByteArray();			return bytes ;		} finally {			try {				bos.close();				oos.close();			} catch (IOException e) {							}					}	}	}