package com.liuyh.generator.domain;

/**
 * 
 * @desc 表过滤 
 * @author Liuyh
 * @date 2019年10月11日下午3:03:11
 */
@FunctionalInterface
public interface TableFilter {
	public boolean filter(String tableName);
}
