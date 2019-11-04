package com.basic.wechat;

import javax.servlet.http.HttpServletRequest;

import com.basic.wechat.tools.WechatTools;

public abstract class BindController {
	
	public String bind(HttpServletRequest request) {
		return WechatTools.checkSignature(request.getQueryString(), getToken());
	}

	protected abstract String getToken();
}