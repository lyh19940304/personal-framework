package com.basic.swagger2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix="swagger2")
@PropertySource(value="swagger.properties",encoding="UTF-8")
@Component
@Getter
@Setter
public class Swagger2Config {
	private String basePackage;
	private String title = "API文档及测试平台";
	private String desc = "减小前后端沟通成本,使用Swagger2来构建RESTful API文档及服务功能测试入口";
	private String serviceUrl = "https://swagger.io/";
	private String version = "2.6.1"; 
}