package com.basic.rabbitmq.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.basic.common.tools.CollectionTools;
import com.basic.rabbitmq.AutoLoadingMQ;
import com.basic.rabbitmq.anno.MessageHandlerType;
import com.basic.rabbitmq.context.MessageHandlerContext;
import com.basic.spring.tools.ClassScaner;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RabbitmqMessageHandlerConfig {

	@Value("${rabbit.basePackages}")
	private String basePackages;
	
	@Autowired
	private AutoLoadingMQ autoLoading;

	@PostConstruct
	public void init() {
		initHandler();
		loading();
	}
	
	private void loading() {
		autoLoading.loading();
	}

	private void initHandler() {
		Map<String, Class<?>> handlerMap = new HashMap<String, Class<?>>();
		ClassScaner.scan(basePackages, MessageHandlerType.class).forEach(clazz -> {
			String type = clazz.getAnnotation(MessageHandlerType.class).value();
			handlerMap.put(type, clazz);
		});
		MessageHandlerContext.putAll(handlerMap);

		StringBuilder sb = new StringBuilder("The RabbitMQ MessageHandlerType Num Is ");
		sb.append(handlerMap.size());
		sb.append(".");
		if (!CollectionTools.isEmpty(handlerMap)) {
			sb.append("As Follows [");
			for (Map.Entry<String, Class<?>> entry : handlerMap.entrySet()) {
				sb.append("{ ");
				sb.append(entry.getKey());
				sb.append(" = ");
				sb.append(entry.getValue().getCanonicalName());
				sb.append(" }");
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
		}
		log.info(sb.toString());
	}
}