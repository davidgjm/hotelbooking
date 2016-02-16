/**
 * 
 */
package com.jinjiang.hotel.config;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author gaojianm
 *
 */
public class HashUtil {

	public static final String encrypt(String raw) {
		if(raw==null||raw.isEmpty()) return null;
		return Base64.encodeBase64String(DigestUtils.sha1(raw));
	}
}
