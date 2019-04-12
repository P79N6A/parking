
package com.zhuyitech.parking.common.async;

/**
 * 异步服务
 *
 * @author walkman
 */
public abstract class InvokeRunnable implements Runnable {

	public void run() {
		onStart();
		try {
			runSafe();
		}
		catch (Exception e) {
			onException(e);
		}
		finally {
			onFinish();
		}
	}
	
	/**
	 * 当run方法执行开始会调用的方法
	 */
	protected void onStart() {
		//to be override
	}
	
	/**
	 * 当run方法执行完毕后会调用的方法
	 */
	protected void onFinish() {
		//to be override
	}
	
	/**
	 * 当run方法执行异常后会调用的方法
	 */
	protected void onException(Exception e) {
		//to be override
	}
	
	public abstract void runSafe();
}
