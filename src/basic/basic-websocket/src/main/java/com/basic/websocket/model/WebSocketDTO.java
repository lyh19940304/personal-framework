package com.basic.websocket.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebSocketDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7228826583376305470L;
	/** 当前连接用户编号 */
	private String userId;
	/** 发送消息的完整内容 */
	private String message;
	/** 发送消息的消息体部分 */
	private String content;
	/** 发送消息的类型部分 */
	private String type;
	/** 发送消息的目标用户编号 */
	private String targetId;
	/** 异常信息 */
	private Throwable error;

	public WebSocketDTO() {
		super();
	}

	/**
	 * 
	 * @desc 连接与断开时使用
	 * @author Liuyh
	 * @date 2019年9月30日上午10:23:55
	 * @param userId
	 */
	public WebSocketDTO( String userId) {
		super();
		this.userId = userId;
	}

	/**
	 * 
	 * @desc 前端发送消息时使用
	 * @author Liuyh
	 * @date 2019年9月30日上午10:24:08
	 * @param userId
	 * @param message
	 */
	public WebSocketDTO(String userId, String message) {
		super();
		this.userId = userId;
		this.message = message;
		/** 不同的项目解析前端发来的message的格式肯定有不同,甚至有些message就只是content,所以这里也可以改,大家知道就好,我就不改了,懒 */
		String[] bodys = message.split("\\|");
		type = bodys[0];
		targetId=bodys[1];
		content=bodys[2];
	}

	/**
	 * 
	 * @desc 异常时使用
	 * @author Liuyh
	 * @date 2019年9月30日上午10:24:30
	 * @param userId
	 * @param error
	 */
	public WebSocketDTO(String userId, Throwable error) {
		super();
		this.userId = userId;
		this.error = error;
	}

}