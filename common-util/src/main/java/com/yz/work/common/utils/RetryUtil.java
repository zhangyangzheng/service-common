package com.yz.work.common.utils;

import java.util.function.Supplier;


public class RetryUtil {
	
	private static int MaxRetryCount = 5;//默认重试5次
	
	private static long MaxSleepTime = 1000;//默认重试间隔1秒
	
	// {{ 重试操作
	public static boolean executeWithRetry(Supplier<Boolean> supplier) {
		return executeWithRetry(supplier, true);
	}
	
	public static boolean executeWithRetry(Supplier<Boolean> supplier, boolean throwIffailed,int retryCount) {
		return executeWithRetry(supplier,throwIffailed,retryCount,MaxSleepTime);
	}
	
	public static boolean executeWithRetry(Supplier<Boolean> supplier, boolean throwIffailed) {
		return executeWithRetry(supplier,throwIffailed,MaxRetryCount);
	}
	
	public static boolean executeWithRetry(Supplier<Boolean> supplier, boolean throwIffailed,int retryCount,Long sleepTime) {
		int retryIndex = 0;
		boolean succeed = false;
		while (retryIndex < retryCount) 
		{
			retryIndex += 1;
			succeed = supplier.get();
			if (!succeed) {
				sleep(sleepTime);
			} else {
				break;
			}
		}
		if (throwIffailed && !succeed) {
			throw new RuntimeException("重试操作失败,请稍后再试");
//			throw new ServiceException(ResponseCode.OPERATION_FAIL, "重试操作失败,请稍后再试");
		}
		return succeed;
	}
	
	private static void sleep(Long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}

	}
	
}
