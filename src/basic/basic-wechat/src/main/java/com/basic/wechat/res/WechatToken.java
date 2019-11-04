package com.basic.wechat.res;

import lombok.Data;

/**
 * 
 * @desc 微信公众号接口获取token返回报文 
 * @author Liuyh
 * @date 2019年10月22日下午3:08:15
 */
@Data
public class WechatToken {
	private String accessToken;
	private long expiresIn;
}