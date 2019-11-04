package com.basic.common.exception;


import com.basic.common.res.BasicEnums;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
/**
 * 自定义异常
 * 
 * @author Liuyh
 * @date 2018年7月17日 下午1:55:58
 */
public class CoreException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String code;
	private String msg;
	
	public CoreException(BasicEnums resultEnums) {
		super(resultEnums.getMsg());
		this.code=resultEnums.getCode();
		this.msg=resultEnums.getMsg();
	}
}