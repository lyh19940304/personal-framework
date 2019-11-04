package com.oceankeeper.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.basic.websocket.context.MessageHandlerContext;
import com.basic.websocket.model.WebSocketDTO;
import com.basic.websocket.strategy.MessageHandlerStrategy;

/**
 * 
 * @desc mq消费者定义 
 * @author Liuyh
 * @date 2019年10月11日下午2:40:46
 */
@Component
@RabbitListener
public class MessageConsumber {

	@Value("${server.port}")
	private int port;
	@RabbitListener(queues = "#{queue.name}")
	public void process(WebSocketDTO request) {
		System.out.println(String.format("port[%s]接受到消息%s", port,JSON.toJSONString(request)));
		
		MessageHandlerStrategy handlerStrategy = MessageHandlerContext.getInstance(request.getType());
		handlerStrategy.handler(request);
	}
}