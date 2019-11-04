package com.oceankeeper.rabbitmq;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.basic.common.tools.ObjectTools;

/**
 * 
 * @desc 队列定义 
 * @author Liuyh
 * @date 2019年10月11日下午2:41:36
 */
@Component
public class QueueConfig {

	private static int port;
	private static String exchange;
	/** 拼接完整的队列名称为 [exchangeName.ip.port] */
	private static String QUEUE_NAME;

	@Bean
	public FanoutExchange exchange() {
		return new FanoutExchange(exchange);
	}

	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME, true);
	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange());
	}

	@Value("${server.port}")
	public void setPort(int port) {
		QueueConfig.port = port;
		initQueueName();
	}

	@Value("${spring.rabbitmq.template.exchange}")
	public void setExChange(String exchange) {
		QueueConfig.exchange = exchange;
		initQueueName();
	}

	public static String getIP() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			return addr.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getExChangeName() {
		return exchange;
	}

	public static int getPort() {
		return port;
	}

	public static void initQueueName() {
		QUEUE_NAME = getExChangeName() + ObjectTools.POINT + getIP() + ObjectTools.POINT + getPort();
	}
}
