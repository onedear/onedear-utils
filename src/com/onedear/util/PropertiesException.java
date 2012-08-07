package com.onedear.util;

/**
 * 类说明：自定义异常类
 * 
 * @version 创建时间：2009-12-23 下午02:54:55
 * @author fisher
 */
public class PropertiesException extends Exception {

	private static final long serialVersionUID = -4707095943074844241L;

	protected Throwable throwable;

	/* 构造方法 */
	public PropertiesException(String msg) {
		super(msg);
	}

	/* 构造方法 */
	public PropertiesException(String msg, Throwable throwable) {
		super(msg);
		this.throwable = throwable;
	}

	/* 得到异常 */
	public Throwable getEx() {
		return this.throwable;
	}

	/* 重写父类方法 */
	@Override
	public void printStackTrace() {
		/* 调用父类的方法 */
		super.printStackTrace();
		if (throwable != null) {
			/* 输出异常 */
			throwable.printStackTrace();
		}

	}

}
