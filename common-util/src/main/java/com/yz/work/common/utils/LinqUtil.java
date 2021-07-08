package com.yz.work.common.utils;

import java.util.*;

public class LinqUtil {
	public static <I,O> Collection<O> select(Collection<I> inputs, SelectFunction<I, O> selectFunc) {
		Collection<O> outPuts = new ArrayList<O>();
		for(I i : inputs) {
			outPuts.add(selectFunc.select(i));
		}
		return outPuts;
	}
	
	
	public static <O> Collection<O> where(Collection<O> inputs, WhereFunction<O> whereFunc) {
		Collection<O> outPuts = new ArrayList<O>();
		for(O o : inputs) {
			if(whereFunc.where(o)) {
				outPuts.add(o);
			}
		}
		
		return outPuts;
	} 
	
	public static <I> Set<I> toSet(Collection<I> inputs) {
		Set<I> result = new HashSet<I>();
		for(I i : inputs) {
			result.add(i);
		}
		return result;
	}
	
	public static <I, K> Map<K, I> toMap(Collection<I> inputs, SelectFunction<I, K> selectFunction) {
		Map<K, I> result = new HashMap<K, I>();
		for(I i : inputs) {
			result.put(selectFunction.select(i), i);
		}
		return result;
	}
	
	public static <I, K> Map<K, Collection<I>> groupBy(Collection<I> inputs, SelectFunction<I, K> selectFunction) {
		Map<K, Collection<I>> result = new HashMap<K, Collection<I>>();
		for(I i : inputs) {
			K key = selectFunction.select(i);
			if(result.containsKey(key)) {
				result.get(key).add(i);
			}
			else {
				List<I> tmp = new ArrayList<I>();
				tmp.add(i);
				result.put(key, tmp);
			}
		}
		return result;
	}
	
	public static <I> boolean any(Collection<I> inputs, SelectFunction<I, Boolean> selectFunction) {
		for(I i: inputs) {
			if(selectFunction.select(i)) {
				return true;
			}
		}
		return false;
	}
	
	public static <I> Integer sum(Collection<I> inputs, SelectFunction<I, Integer> selectFunction) {
		Integer result = 0;
		for(I i : inputs) {
			result+=selectFunction.select(i);
		}
		return result;
	}
}
