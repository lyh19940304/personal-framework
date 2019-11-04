package com.basic.rabbitmq.model;

import lombok.Data;

@Data
public class MessageDTO {
	private String consumerTag;
	private long deliveryTag;
	private boolean redeliver;
	private String exchange;
	private String routingKey;
	private String content;
}
