package com.basic.mapper;

public interface BaseMapper<MODEL,KEYS> {
	
	int insert(MODEL model);
	
	int insertSelective(MODEL model);
	
	int deleteByPrimaryKey(KEYS keys);
	
	int updateByPrimaryKey(MODEL model);

	int updateByPrimaryKeySelective(MODEL model);
	
	MODEL selectByPrimaryKey(KEYS keys);
}