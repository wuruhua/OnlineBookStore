package com.bdqn.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * 业务逻辑层工具类
 * 
 * @author Administrator
 * 
 */
public class BizUtils {

	/**
	 * 对密码进行MD5加密
	 * 
	 * @param oldPwd
	 * @return
	 */
	public static String getMd5EncodePwd(String oldPwd) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] pwdBytes = md.digest(oldPwd.getBytes());

			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(pwdBytes);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
