package com.basic.websocket.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.basic.spring.tools.ClassScaner;
import com.basic.websocket.anno.MessageHandlerType;
import com.basic.websocket.context.MessageHandlerContext;

@Configuration
public class WebSocketConfig {
	
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}
	
	@Value("${websocket.basePackages}")
	private String basePackages;
	
	@PostConstruct
	public void init() {
		Map<String, Class<?>> handlerMap = new HashMap<String, Class<?>>();
		ClassScaner.scan(basePackages, MessageHandlerType.class).forEach(clazz -> {
			String type = clazz.getAnnotation(MessageHandlerType.class).value();
			handlerMap.put(type, clazz);
		});
		MessageHandlerContext.putAll(handlerMap);
	}
}