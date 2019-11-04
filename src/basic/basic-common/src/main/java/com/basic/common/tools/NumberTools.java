package com.basic.common.tools;

import java.math.BigDecimal;

/**
 * 
 * @desc 数值工具类
 * @author Liuyh
 * @date 2018年9月4日 上午11:54:25
 */
public class NumberTools {

	private static final int DEF_DIV_SCALE = 2;

	/**
	 * 
	 * @desc 参数1是否大于参数2
	 * @author Liuyh
	 * @date 2018年8月30日下午5:31:46
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean greaterThan(BigDecimal v1, BigDecimal v2) {
		return v1.compareTo(v2) == 1;
	}

	/**
	 * 
	 * @desc 参数1是否小于参数2
	 * @author Liuyh
	 * @date 2018年8月30日下午5:31:35
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean lessThan(BigDecimal v1, BigDecimal v2) {
		return v1.compareTo(v2) == -1;
	}

	/**
	 * 
	 * @desc 参数1是否等于参数2
	 * @author Liuyh
	 * @date 2018年8月30日下午5:31:53
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean beEqualsTo(BigDecimal v1, BigDecimal v2) {
		return v1.compareTo(v2) == 0;
	}

	/**
	 * 
	 * @desc 参数1是否大于等于参数2
	 * @author Liuyh
	 * @date 2018年8月30日下午5:35:43
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean greaterThanAndEquals(BigDecimal v1, BigDecimal v2) {
		return greaterThan(v1, v2) || beEqualsTo(v1, v2);
	}

	/**
	 * 
	 * @desc 参数1是否小于等于参数2
	 * @author Liuyh
	 * @date 2018年8月30日下午5:35:53
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean lessThanAndEquals(BigDecimal v1, BigDecimal v2) {
		return lessThan(v1, v2) || beEqualsTo(v1, v2);
	}

	/**
	 * 
	 * @desc 加法运算
	 * @author Liuyh
	 * @date 2018年9月4日上午11:53:07
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static BigDecimal add(BigDecimal b1, BigDecimal b2) {
		return b1.add(b2);
	}

	/**
	 * 
	 * @desc 减法运算
	 * @author Liuyh
	 * @date 2018年9月4日上午11:53:14
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static BigDecimal sub(BigDecimal b1, BigDecimal b2) {
		return b1.subtract(b2);
	}

	/**
	 * 
	 * @desc 乘法运算
	 * @author Liuyh
	 * @date 2018年9月4日上午11:53:24
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static BigDecimal mul(BigDecimal b1, BigDecimal b2) {
		return b1.multiply(b2);
	}

	/**
	 * 
	 * @desc 除法运算
	 * @author Liuyh
	 * @date 2018年9月4日上午11:52:04
	 * @param b1 除数
	 * @param b2 被除数
	 * @return
	 */
	public static BigDecimal div(BigDecimal b1, BigDecimal b2) {
		return div(b1, b2, DEF_DIV_SCALE);
	}

	/**
	 * 
	 * @desc
	 * @author Liuyh
	 * @date 2018年9月4日上午11:50:10
	 * @param b1    除数
	 * @param b2    被除数
	 * @param scale 保留几位小数
	 * @return
	 */
	public static BigDecimal div(BigDecimal b1, BigDecimal b2, int scale) {
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
	}

	public static void main(String[] args) {
		BigDecimal b1 = new BigDecimal("1.0");
		BigDecimal b2 = new BigDecimal("2.0");
		System.out.println(div(b1, b2));
	}
}