package com.basic.common.tools;

import com.basic.common.exception.CoreException;
import com.basic.common.res.BasicEnums;

/**
 * 
 * @desc 精简代码开发工具类 
 * @author Liuyh
 * @date 2019年10月11日下午2:59:59
 */
public class SimpleDevTools {

	/**
	 * 
	 * @desc 表达式为true 返回指定返回码
	 * @author Liuyh
	 * @date 2019年9月29日下午4:04:06
	 * @param enums
	 * @param flag
	 */
	public static void judge(BasicEnums enums, boolean flag) {
		if (flag) {
			throw new CoreException(enums);
		}
			
	}

	/**
	 * 
	 * @desc 表达式任意为true 返回指定返回码
	 * @author Liuyh
	 * @date 2019年9月29日下午4:06:06
	 * @param enums
	 * @param flags
	 */
	public static void judge(BasicEnums enums, boolean... flags) {
		for (boolean flag : flags) {
			judge(enums, flag);
		}
	}
}