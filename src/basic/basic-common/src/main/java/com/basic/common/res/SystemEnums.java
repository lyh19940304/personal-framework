package com.basic.common.res;

import lombok.Getter;

/**
 * 
 * @desc 00000000
 * @author Liuyh
 * @date 2019年9月29日下午3:44:13
 */
@Getter
public enum SystemEnums implements BasicEnums {
	/** 业务操作成功 */
	SUCCESS("00000000", "成功"), 
	/** 时间格式不正确 */
	TIME_DATA_FORMAT_FAIL("00000097","时间格式不正确 yyyy-MM-dd HH:mm:ss"),
	/** 日期格式不正确 */
	DATE_DATA_FORMAT_FAIL("00000098","日期格式不正确 yyyy-MM-dd"),
	/** 系统异常 */
	EXCEPTION("00000099", "系统异常"),
	;

	private final String code;
	private final String msg;

	private SystemEnums(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}