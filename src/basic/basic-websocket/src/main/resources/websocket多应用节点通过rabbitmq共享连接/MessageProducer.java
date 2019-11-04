package com.oceankeeper.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.basic.common.tools.ObjectTools;
import com.basic.websocket.model.WebSocketDTO;

/**
 * 
 * @desc mq生产者定义 
 * @author Liuyh
 * @date 2019年10月11日下午2:40:59
 */
@Component
public class MessageProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void send(WebSocketDTO request) {
		rabbitTemplate.convertAndSend(rabbitTemplate.getExchange(),ObjectTools.EMPTY,request);
	}
}