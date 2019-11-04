package com.basic.rabbitmq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.basic.rabbitmq.model.RabbitConf;


@Mapper
public interface RabbitConfMapper{
	public List<RabbitConf> getLists();
}