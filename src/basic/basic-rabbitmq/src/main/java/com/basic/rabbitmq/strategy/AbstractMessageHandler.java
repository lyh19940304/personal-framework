package com.basic.rabbitmq.strategy;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.basic.common.exception.CoreException;
import com.basic.common.res.Result;
import com.basic.rabbitmq.model.MessageDTO;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractMessageHandler implements MessageHandlerStrategy {

	@Override
	public Result<Void> handlerMessage(MessageDTO messageDTO, Channel channel) {
		try {
			Result<Void> result = handlerMessage(messageDTO);
			channel.basicAck(messageDTO.getDeliveryTag(), false);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return Result.exception("MQ消息确认失败");
		} catch(CoreException e) {
			return Result.business(e.getCode(), e.getMsg());
		}
	}

	@Override
	public Result<Void> handlerMessage(MessageDTO messageDTO) {
		log.debug("mq receiver detail : {}", JSON.toJSONString(messageDTO));
		Result<Void> result = handler(messageDTO);
		return result;
	}

	public abstract Result<Void> handler(MessageDTO messageDTO);
}