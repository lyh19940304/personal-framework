package com.basic.wechat.res;

import lombok.Data;

/**
 * 
 * @desc 微信公众号接口返回报文 
 * @author Liuyh
 * @date 2019年10月22日下午3:07:58
 */
@Data
public class WechatAPIResponse {
	private String errcode;
	private String errmsg;
	private String msgid;
}
