package com.basic.rabbitmq.strategy;

import com.basic.common.res.Result;
import com.basic.rabbitmq.model.MessageDTO;
import com.rabbitmq.client.Channel;

public interface MessageHandlerStrategy {
	/**
	 * 
	 * @desc 手动确认消息模式 
	 * @author Liuyh
	 * @date 2019年11月1日下午9:12:03
	 * @param messageDTO
	 * @param channel
	 * @return
	 */
	Result<Void> handlerMessage(MessageDTO messageDTO,Channel channel);
	/**
	 * 
	 * @desc 自动确认消息模式 
	 * @author Liuyh
	 * @date 2019年11月1日下午9:12:20
	 * @param messageDTO
	 * @return
	 */
	Result<Void> handlerMessage(MessageDTO messageDTO);
}