package com.basic.websocket.strategy;

import com.basic.common.res.Result;
import com.basic.websocket.model.WebSocketDTO;

public interface MessageHandlerStrategy {
	
	/**
	 * 
	 * @desc 处理前端发送消息
	 * @author Liuyh
	 * @date 2019年9月30日上午10:35:06
	 * @param request
	 * @return
	 */
	Result<Void> handler(WebSocketDTO request);
}