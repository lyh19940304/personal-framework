package com.basic.swagger2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2 {
	@Autowired
	private Swagger2Config swaggerConfig;

	@Bean
	public Docket createRestApi() {
		ParameterBuilder accessToken = new ParameterBuilder();
		List<Parameter> params = new ArrayList<Parameter>();
		accessToken.name("accessToken").description("认证令牌").modelRef(new ModelRef("String")).parameterType("header").required(false).build();
		params.add(accessToken.build());
		
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage(swaggerConfig.getBasePackage())).paths(PathSelectors.any())
				.build().globalOperationParameters(params);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(swaggerConfig.getTitle()).description(swaggerConfig.getDesc())
				.termsOfServiceUrl(swaggerConfig.getServiceUrl()).version(swaggerConfig.getVersion()).build();
	}
}