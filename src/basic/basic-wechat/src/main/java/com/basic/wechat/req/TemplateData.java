package com.basic.wechat.req;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @desc 模板数据 
 * @author Liuyh
 * @date 2019年10月22日下午4:35:55
 */
@Getter
@Setter
public class TemplateData {
	private String value;
	private String color;
	
	public TemplateData() {
		super();
	}
	public TemplateData(String value) {
		super();
		this.value = value;
	}
	public TemplateData(String value, String color) {
		super();
		this.value = value;
		this.color = color;
	}
}