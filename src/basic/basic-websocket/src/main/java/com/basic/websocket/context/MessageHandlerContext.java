package com.basic.websocket.context;

import java.util.HashMap;
import java.util.Map;

import com.basic.common.exception.CoreException;
import com.basic.spring.SpringContextUtils;
import com.basic.websocket.enums.WebSocketEnums;
import com.basic.websocket.strategy.MessageHandlerStrategy;

public class MessageHandlerContext {

	private static Map<String, Class<?>> handlerMap=new HashMap<String, Class<?>>();
	public static void putAll(Map<String, Class<?>> map) {
		handlerMap.putAll(map);
	}
	public static MessageHandlerStrategy getInstance(String type) {
		Class<?> clazz=handlerMap.get(type);
		if(clazz==null) {
			throw new CoreException(WebSocketEnums.NOT_FOUND_HANDLER_TYPE);
		}
		return (MessageHandlerStrategy) SpringContextUtils.getBean(clazz);
	}
}