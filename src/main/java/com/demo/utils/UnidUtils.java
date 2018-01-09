package com.demo.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class UnidUtils {

	private static int UNID_LENGTH = 20;
	private static int PREFIX_LENGTH=10;

	public static String generateID() {
		String systime = String.valueOf(System.currentTimeMillis());
		return RandomStringUtils.randomAlphabetic(
				UNID_LENGTH - systime.length()).toLowerCase()
				+ systime;
	}
	/**
	 * 获取带前缀的唯一标识
	 * 
	 * @param prefix
	 * @return
	 * @throws Exception 
	 */
	public static String generateID(String prefix) throws Exception {
		if (StringUtils.isBlank(prefix)) {
			return generateID();
		}
		int pl = prefix.length();
		if (pl > PREFIX_LENGTH) {
			throw new Exception("the length of prefix(" + prefix
					+ ") is larger than " + PREFIX_LENGTH + ",please reset!");
		}
		return prefix
				+ RandomStringUtils.randomAlphabetic(UNID_LENGTH - pl)
						.toLowerCase();
	}
}
