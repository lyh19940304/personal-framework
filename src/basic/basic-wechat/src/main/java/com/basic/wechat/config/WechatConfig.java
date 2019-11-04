package com.basic.wechat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix="wechat")
@PropertySource(value="wechat.properties",encoding="UTF-8")
@Component
@Getter
@Setter
public class WechatConfig {
	private String appId;
	private String appSecret;
	/** 要求完整的tokenUrl中的appId与appSecret的值使用 %s 占位符占位   */
	private String tokenUrl;
	/** 要求完整的url中的access_token的值使用 %s 占位符占位  */
	private String pushMessageTemplateUrl;
}