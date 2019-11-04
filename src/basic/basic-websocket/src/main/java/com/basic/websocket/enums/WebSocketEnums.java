package com.basic.websocket.enums;

import com.basic.common.res.BasicEnums;

import lombok.Getter;

@Getter
public enum WebSocketEnums implements BasicEnums{
	/** 发送消息类型有误 */
	NOT_FOUND_HANDLER_TYPE("00000101","发送消息类型有误"),
	;
	private WebSocketEnums(String code,String msg) {
		this.code=code;
		this.msg=msg;
	}
	private final String code;
	private final String msg;
}
