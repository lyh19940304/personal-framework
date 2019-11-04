package com.basic.wechat.tools;

import java.util.ArrayList;
import java.util.Collections;

import com.basic.encryption.SHA1Util;

/**
 * 
 * @desc 微信公众号相关工具类 
 * @author Liuyh
 * @date 2019年10月22日下午3:16:45
 */
public class WechatTools {
	/**
	 * 
	 * @desc 与公众号绑定认证
	 * @author Liuyh
	 * @date 2019年10月22日下午3:16:58
	 * @param params
	 * @return
	 */
	public static String checkSignature(String params,String token) {
		String[] content = params.split("&");
		String signature = content[0].split("=")[1];
		String timestamp = content[2].split("=")[1];
		String nonce = content[3].split("=")[1];

		ArrayList<String> list = new ArrayList<String>();
		list.add(nonce);
		list.add(timestamp);
		list.add(token);

		// 字典序排序
		Collections.sort(list);
		// SHA1加密
		String checksignature = SHA1Util.encode(list.get(0) + list.get(1) + list.get(2));

		if (checksignature.equals(signature)) {
			return content[1].split("=")[1];
		}
		return null;
	}
}
