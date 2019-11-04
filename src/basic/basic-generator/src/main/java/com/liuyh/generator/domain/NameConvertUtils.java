package com.liuyh.generator.domain;

/**
 * 
 * @desc 名称转换工具类 
 * @author Liuyh
 * @date 2019年10月11日下午3:01:41
 */
public class NameConvertUtils {

	/**
	 *  首字母小写驼峰命名 常用于属性
	 * @desc
	 * @author Liuyh
	 * @date 2018年8月7日上午10:00:04
	 * @param para
	 * @return
	 */
	public static String underlineToHump(String para) {
		StringBuilder result = new StringBuilder();
		String a[] = para.split("_");
		for (String s : a) {
			if (result.length() == 0) {
				result.append(s.toLowerCase());
			} else {
				result.append(s.substring(0, 1).toUpperCase());
				result.append(s.substring(1).toLowerCase());
			}
		}
		return result.toString();
	}

	/**
	 * 首字母大写驼峰命名 常用语类名
	 * @desc
	 * @author Liuyh
	 * @date 2018年8月7日上午10:00:32
	 * @param para
	 * @return
	 */
	public static String underlineToHumpFirstUpper(String para) {
		StringBuilder result = new StringBuilder();
		String a[] = para.split("_");
		for (String s : a) {
			result.append(s.substring(0, 1).toUpperCase());
			result.append(s.substring(1).toLowerCase());
		}
		return result.toString();
	}
}