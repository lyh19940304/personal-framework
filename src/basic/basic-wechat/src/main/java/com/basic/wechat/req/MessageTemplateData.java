package com.basic.wechat.req;

import java.util.Map;

import lombok.Data;

/**
 * 
 * @desc 消息模板格式 
 * @author Liuyh
 * @date 2019年10月22日下午4:35:40
 */
@Data
public class MessageTemplateData {
	
	/** 接受者openId */
	private String touser;
	/** 模板内容字体颜色,默认为黑色 */
	private String topcolor;
	/** 模板数据 */
	private Map<String, TemplateData> data;
	/** 模板ID */
	private String templateId;
	/** 模板跳转链接(海外账号没有跳转能力) */
	private String url;
	
}
