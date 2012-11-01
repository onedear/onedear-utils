package com.onedear.exceptions;


/**
 * <br>==========================
 * <br> @company ：优视科技
 * <br> @author  ：邱志明(onedear)
 * <br> @version ：1.0 
 * <br> @date    : 2012-10-22下午12:30:28
 * <br>==========================
 */
public class PackageNotFoundException extends RuntimeException {
	public PackageNotFoundException() {}
	
	public PackageNotFoundException(String message) {
		super(message);
	}
}
