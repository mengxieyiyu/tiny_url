package com.tinyu.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

	/**
	 * MD5 encrypt
	 * 
	 * @param data
	 *            the content
	 * @return byte[] Message Summary
	 * 
	 * @throws Exception
	 */
	public static byte[] encodeMD5(String data) throws Exception {

		// execute Message Summary
		return DigestUtils.md5(data);
	}

	/**
	 * MD5 encrypt
	 * 
	 * @param data
	 *            the content
	 * @return byte[] Message Summary
	 * 
	 * @throws Exception
	 */
	public static String encodeMD5Hex(String data) {
		// execute Message Summary
		return DigestUtils.md5Hex(data);
	}
}
