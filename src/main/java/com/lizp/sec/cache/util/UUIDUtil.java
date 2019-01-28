package com.lizp.sec.cache.util;

import java.util.UUID;

public class UUIDUtil {

	public static String getUUIDString() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
