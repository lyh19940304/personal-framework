package com.basic.rabbitmq.context;

import java.util.HashMap;
import java.util.Map;

import com.basic.common.exception.CoreException;
import com.basic.rabbitmq.enums.RabbitEnums;
import com.basic.rabbitmq.strategy.MessageHandlerStrategy;
import com.basic.spring.SpringContextUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageHandlerContext {
	
	private static Map<String, Class<?>> handlerMap=new HashMap<String, Class<?>>();

	public static void putAll(Map<String, Class<?>> map) {
		handlerMap.putAll(map);
	}
	
	public static MessageHandlerStrategy getInstance(String type) {
		Class<?> clazz=handlerMap.get(type);
		if(clazz==null) {
			log.error("错误的消息处理类型[{}]",type);
			throw new CoreException(RabbitEnums.NOT_FOUND_HANDLER_TYPE);
		}
		return (MessageHandlerStrategy) SpringContextUtils.getBean(clazz);
	}
}