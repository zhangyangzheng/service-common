package com.yz.work.common.utils;


public interface SelectFunction<I, O> {
	public O select(I inputs); 
}
