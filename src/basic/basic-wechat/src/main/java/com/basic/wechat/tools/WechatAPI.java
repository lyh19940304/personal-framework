package com.basic.wechat.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.basic.wechat.config.WechatConfig;
import com.basic.wechat.req.MessageTemplateData;
import com.basic.wechat.res.WechatAPIResponse;
import com.basic.wechat.res.WechatToken;

/**
 * 
 * @desc 微信接口调用包装 
 * @author Liuyh
 * @date 2019年10月22日下午8:59:07
 */
@Component
public class WechatAPI {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WechatConfig wechatConfig;
	
	/**
	 * 
	 * @desc 获取微信token 
	 * @author Liuyh
	 * @date 2019年10月22日下午3:47:58
	 * @return
	 */
	public WechatToken getToken() {
		if(wechatConfig==null) {
			throw new IllegalStateException("You must configure the Class \"com.basic.wechat.config.WechatConfig\" in File [wechat.properties]");
		}
		String url=wechatConfig.getTokenUrl();
		int first=url.indexOf("%s");
		int last=url.lastIndexOf("%s");
		if(first<0||last<0 || first==last) {
			throw new IllegalStateException("the Field \"tokenUrl\" of Class \"com.basic.wechat.config.WechatConfig\" must have two \"%s\" placeholder.The first is a placeholder for \"appId\" and another is a placeholder for \"appSecret\".");
		}
		
		url=String.format(url, wechatConfig.getAppId(),wechatConfig.getAppSecret());
		return restTemplate.postForObject(url, null, WechatToken.class);
	}
	/**
	 * 
	 * @desc 发送模板消息 
	 * @author Liuyh
	 * @date 2019年10月22日下午4:44:37
	 * @param messageTemplateData
	 * @param token
	 * @return
	 */
	public WechatAPIResponse pushMessageTemplate(MessageTemplateData messageTemplateData,String token) {
		if(wechatConfig==null) {
			throw new IllegalStateException("You must configure the Class \"com.basic.wechat.config.WechatConfig\" in File [wechat.properties]");
		}
		String url=wechatConfig.getPushMessageTemplateUrl();
		int index=url.indexOf("%s");
		if(index<0) {
			throw new IllegalStateException("the Field \"pushMessageTemplateUrl\" of Class \"com.basic.wechat.config.WechatConfig\" must have one \"%s\" in place of it! ");
		}
		
		url=String.format(url, token);
		return restTemplate.postForObject(url, messageTemplateData, WechatAPIResponse.class);
		
	}
}