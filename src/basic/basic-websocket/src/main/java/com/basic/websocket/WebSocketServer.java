package com.basic.websocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;

import com.basic.websocket.model.WebSocketDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebSocketServer {

	private static ConcurrentHashMap<String, Session> webSockets = new ConcurrentHashMap<String, Session>();

	public ConcurrentHashMap<String, Session> getWebSockets() {
		return webSockets;
	}

	/**
	 * 
	 * @desc 连接时回调
	 * @author Liuyh
	 * @date 2019年9月29日下午8:55:02
	 * @param session
	 * @param userId
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("userId") String userId) {
		webSockets.put(userId, session);
		log.info(String.format("用户编号[%s]加入连接", userId));

		WebSocketDTO request = new WebSocketDTO(userId);
		open(request);
	}

	public void open(WebSocketDTO request) {

	}

	/**
	 * 
	 * @desc 断开时回调
	 * @author Liuyh
	 * @date 2019年9月29日下午8:55:13
	 * @param userId
	 */
	@OnClose
	public void onClose(Session session, @PathParam("userId") String userId) {
		webSockets.remove(userId);
		log.info(String.format("用户编号[%s]断开连接", userId));
		WebSocketDTO request = new WebSocketDTO(userId);
		close(request);
	}

	public void close(WebSocketDTO request) {

	}

	/**
	 * 
	 * @desc 前端发消息的回调
	 * @author Liuyh
	 * @date 2019年9月29日下午8:55:20
	 * @param userId  发送消息的用户编号
	 * @param message 发送的内容
	 */
	@OnMessage
	public void onMessage(Session session, @PathParam("userId") String userId, String message) {
		log.info(String.format("用戶编号[%s]发送消息[%s]", userId, message));

		/** 内容处理不一致的话,使用策略模式维护 */
		/** 集群节点的话 先把消息发送到MQ */
		/** 所有节点接受消息并处理 因为分片所以不会有重复发送的问题 但可能会有无法监控发送结果的问题 */
		WebSocketDTO request = new WebSocketDTO(userId, message);
		message(request);
	}

	public void message(WebSocketDTO request) {

	}

	@OnError
	public void onError(Session session, Throwable error) {
		log.error(String.format("websocket onError回调", error));

		WebSocketDTO request = new WebSocketDTO(null, error);
		error(request);
	}

	public void error(WebSocketDTO request) {
	}

	public void sendMessage() {

	}

	/**
	 * 
	 * @desc 发送消息 可以当做消息处理的策略模式的一种实现
	 * @author Liuyh
	 * @date 2019年9月29日下午9:05:24
	 * @param sendUserId
	 * @param message
	 */
	public static void sendMessage(WebSocketDTO request) {
		Session session = null;
		try {
			session = webSockets.get(request.getTargetId());
			if (session != null) {
				session.getBasicRemote().sendText(request.getContent());
			} else {
				session = webSockets.get(request.getUserId());
				if (session != null) {
					session.getBasicRemote().sendText(String.format("用户编号[%s]已离线", request.getTargetId()));
				}
			}
		} catch (IOException e) {
			log.error(String.format("发送消息异常\nsendUserId:[%s]\nmessage:[%s]"));
		}
	}

	/**
	 * 
	 * @desc 群发消息 可以当做消息处理的策略模式的一种实现
	 * @author Liuyh
	 * @date 2019年9月29日下午9:12:36
	 * @param message
	 */
	public static void sendMessageEveryOne(String message) {
		String[] bodys = message.split("|");

		String content = bodys[1];
		try {
			for (String userId : webSockets.keySet()) {
				webSockets.get(userId).getBasicRemote().sendText(content);
			}
		} catch (IOException e) {
			log.error(String.format("群发消息异常[%s]", message), e);
		}
	}
}