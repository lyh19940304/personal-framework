package com.basic.rabbitmq.enums;

import com.basic.common.res.BasicEnums;

import lombok.Getter;

@Getter
public enum RabbitEnums implements BasicEnums {
	NOT_FOUND_HANDLER_TYPE("00000201","处理消息类型有误"),
	;
	private RabbitEnums(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private final String code;
	private final String msg;
}
