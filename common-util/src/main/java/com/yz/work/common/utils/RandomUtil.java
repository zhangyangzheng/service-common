package com.yz.work.common.utils;

import java.util.UUID;


public final class RandomUtil {

	public static long generateLongUUID() {
		UUID initUUID = UUID.randomUUID();
		long uuid = Math.abs(initUUID.getLeastSignificantBits() + initUUID.getMostSignificantBits());
		return uuid;
	}

	public static long generateLongUUID(UUID initUUID) {
		long uuid = Math.abs(initUUID.getLeastSignificantBits() + initUUID.getMostSignificantBits());
		return uuid;
	}
}
