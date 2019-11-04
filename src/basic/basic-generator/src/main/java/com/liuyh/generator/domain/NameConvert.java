package com.liuyh.generator.domain;

/**
 * 
 * @desc 命名转换 
 * @author Liuyh
 * @date 2019年9月29日下午1:10:00
 */
@FunctionalInterface
public interface NameConvert {
	String convert(String name);
}
