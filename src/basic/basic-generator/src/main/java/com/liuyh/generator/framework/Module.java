package com.liuyh.generator.framework;

import lombok.Getter;

/**
 * 
 * @desc 生成代码的模块 
 * @author Liuyh
 * @date 2019年10月11日下午3:03:58
 */
@Getter
public enum Module {

	/** api */
	API("api","api", "接口"),
	/** biz */
	BIZ("biz","biz", "业务"),
	/** db */
	DATA_ACCESS("data-access","data.access","数据库层"),
	/** generator */
	GENERATOR("generator","generator", "代码生成"),
	/** model */
	MODEL("model","model", "模型"),
	/** service */
	SERVICE("service","service", "服务"),
	;
	
	private final String code;
	private final String property;
	private final String desc;
	
	private Module(String code,String property, String desc) {
		this.code = code;
		this.property=property;
		this.desc = desc;
	}
}
