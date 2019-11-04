package com.basic.common.res;

import com.basic.common.tools.ObjectTools;

/**
 * 
 * @desc 返回码由8位构成 1.2位:系统编号 3.4位:模块编号 5.6位:类型编号 7.8位:顺序编号 00表示共用
 * @author Liuyh
 * @date 2019年2月26日 下午6:37:47
 */
public interface BasicEnums {
	String getCode();

	String getMsg();

	public static String getMsgByCode(BasicEnums[] gfwlkjEnums, String code) {
		for (BasicEnums enums : gfwlkjEnums) {
			if (enums.getCode().equals(code)) {
				return enums.getMsg();
			}
		}
		return ObjectTools.EMPTY;
	}

	public static boolean isLegal(BasicEnums[] gfwlkjEnums, String code) {
		return BasicEnums.getMsgByCode(gfwlkjEnums, code).equals(ObjectTools.EMPTY);
	}

}