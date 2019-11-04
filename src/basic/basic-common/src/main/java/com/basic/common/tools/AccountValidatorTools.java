package com.basic.common.tools;

import java.util.regex.Pattern;

/**
 * 
 * @desc 数据校验工具类 
 * @author Liuyh
 * @date 2019年10月11日下午2:58:51
 */
public class AccountValidatorTools {
	/**
	 * 正则表达式：验证用户名
	 */
	public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,20}$";

	/**
	 * 正则表达式：验证密码
	 */
	public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";

	/**
	 * 正则表达式：验证手机号
	 */
	public static final String REGEX_MOBILE = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";

	/**
	 * 正则表达式：验证邮箱
	 */
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	/**
	 * 正则表达式：验证汉字
	 */
	public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

	/**
	 * 正则表达式：验证身份证
	 */
	public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
	
	
	/**
	 * 正则表达式：验证护照
	 */
	public static final String PASSPORT= "/^[a-zA-Z]{5,17}$/";
	
	/** 
	 * 港澳通行证验证   
	 */
	public static final String HKMAKAO = "/^[HMhm]{1}([0-9]{10}|[0-9]{8})$/";
	 /** 
	  * 台湾通行证验证  
	  */
	public static final String TAIWAN = " /^[0-9]{10}$/";

	/**
	 * 正则表达式：验证URL
	 */
	public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

	/**
	 * 正则表达式：验证IP地址
	 */
	public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

	/**
	 * 校验用户名
	 * 
	 * @param username
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isUsername(String username) {
		return Pattern.matches(REGEX_USERNAME, username);
	}

	/**
	 * 校验密码
	 * 
	 * @param password
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isPassword(String password) {
		return Pattern.matches(REGEX_PASSWORD, password);
	}

	/**
	 * 校验手机号
	 * 
	 * @param mobile
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isMobile(String mobile) {
		return Pattern.matches(REGEX_MOBILE, mobile);
	}

	/**
	 * 校验邮箱
	 * 
	 * @param email
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isEmail(String email) {
		return Pattern.matches(REGEX_EMAIL, email);
	}

	/**
	 * 校验汉字
	 * 
	 * @param chinese
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isChinese(String chinese) {
		return Pattern.matches(REGEX_CHINESE, chinese);
	}

	/**
	 * 校验身份证
	 * 
	 * @param idCard
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isIdCard(String idCard) {
		return Pattern.matches(REGEX_ID_CARD, idCard);
	}
	
	/**
	 * 
	 * @desc 校验护照
	 * @author Zhangfz
	 * @date 2018年9月29日 下午4:44:24
	 * @param passport
	 * @return
	 */
	public static boolean isPassport(String passport) {
		return Pattern.matches(PASSPORT, passport);
	}
	
	/**
	 * 
	 * @desc 校验港澳通行证
	 * @author Zhangfz
	 * @date 2018年9月29日 下午4:47:33
	 * @param hkmakao
	 * @return
	 */
	public static boolean isHkmakao(String hkmakao) {
		return Pattern.matches(HKMAKAO, hkmakao);
	}
	
	/**
	 * 
	 * @desc 校验台湾通行证
	 * @author Zhangfz
	 * @date 2018年9月29日 下午4:47:55
	 * @param taiwan
	 * @return
	 */
	public static boolean isTaiwan(String taiwan) {
		return Pattern.matches(TAIWAN, taiwan);
	}

	/**
	 * 校验URL
	 * 
	 * @param url
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isUrl(String url) {
		return Pattern.matches(REGEX_URL, url);
	}

	/**
	 * 校验IP地址
	 * 
	 * @param ipAddr
	 * @return
	 */
	public static boolean isIpAddr(String ipAddr) {
		return Pattern.matches(REGEX_IP_ADDR, ipAddr);
	}
}
