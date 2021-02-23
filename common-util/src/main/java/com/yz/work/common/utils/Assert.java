package com.yz.work.common.utils;


public abstract class Assert {

	public static <T extends RuntimeException> void isTrue(boolean expression, T e) {
		if (!expression) {
			throw e;
		}
	}
	
	public static <T extends RuntimeException> void isTrue(boolean expression, FailureAction action) {
		if (!expression) {
			action.run();
		}
	}
	
	@FunctionalInterface
	public interface FailureAction {
		void run();
	}

}
