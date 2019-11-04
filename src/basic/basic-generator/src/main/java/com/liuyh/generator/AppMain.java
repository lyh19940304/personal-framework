package com.liuyh.generator;

import com.liuyh.generator.framework.FrameworkGenerator;

/**
 * 
 * @desc 应用启动类 
 * @author Liuyh
 * @date 2019年10月11日下午3:00:17
 */
public class AppMain {
	public static void main(String[] args) {
		try {
			new FrameworkGenerator().generator();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
