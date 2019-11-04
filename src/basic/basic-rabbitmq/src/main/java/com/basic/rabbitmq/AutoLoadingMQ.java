package com.basic.rabbitmq;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.basic.common.tools.ObjectTools;
import com.basic.rabbitmq.context.MessageHandlerContext;
import com.basic.rabbitmq.mapper.RabbitConfMapper;
import com.basic.rabbitmq.model.MessageDTO;
import com.basic.rabbitmq.model.RabbitConf;
import com.basic.rabbitmq.strategy.MessageHandlerStrategy;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

@Component
public class AutoLoadingMQ {

	@Autowired
	private RabbitConfMapper rabbitConfMapper;

	public void loading() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<RabbitConf> lists = rabbitConfMapper.getLists();
		for (RabbitConf conn : lists) {
			conf(conn);
		}
	}

	private void conf(RabbitConf conn) {
		ConnectionFactory connectionFactory = null;
		Connection connection = null;
		try {
			connectionFactory = new ConnectionFactory();
			
			connectionFactory.setRequestedChannelMax(5);
			
			connectionFactory.setHost(conn.getHost());
			connectionFactory.setVirtualHost(conn.getVHost());
			connectionFactory.setPort(conn.getPort());
			connectionFactory.setUsername(conn.getUsername());
			connectionFactory.setPassword(conn.getPassword());

			connection = connectionFactory.newConnection();
			final Channel channel = connection.createChannel();
			channel.basicQos(1);

			channel.queueDeclare(conn.getQueue(), false, false, false, null);

			if (!ObjectTools.isBlank(conn.getExchangeType())) {
				BuiltinExchangeType exchangeType = BuiltinExchangeType.valueOf(conn.getExchangeType());
				if (exchangeType == null) {
					throw new IllegalStateException(
							String.format("exchange type config error from db :%s", JSON.toJSONString(conn)));
				}
				channel.exchangeDeclare(conn.getExchangeName(), conn.getExchangeType());
			}
			if (!ObjectTools.isBlank(conn.getExchangeName())) {
				channel.queueBind(conn.getQueue(), conn.getExchangeName(), conn.getRoutingKey());
			}

			Consumer callback = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
						byte[] body) throws IOException {
					MessageDTO messageDTO = new MessageDTO();
					messageDTO.setConsumerTag(consumerTag);
					messageDTO.setDeliveryTag(envelope.getDeliveryTag());
					messageDTO.setRedeliver(envelope.isRedeliver());
					messageDTO.setExchange(envelope.getExchange());
					messageDTO.setRoutingKey(envelope.getRoutingKey());
					messageDTO.setContent(new String(body, "UTF-8"));

					MessageHandlerStrategy instance = MessageHandlerContext.getInstance(conn.getCallback());
					if (conn.isConfirm()) {
						instance.handlerMessage(messageDTO);
					} else {
						instance.handlerMessage(messageDTO, channel);
					}
				}
			};
			channel.basicConsume(conn.getQueue(), conn.isConfirm(), callback);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}