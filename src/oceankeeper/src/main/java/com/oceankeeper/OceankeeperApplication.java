package com.oceankeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @desc Oceankeeper启动类 
 * @author Liuyh
 * @date 2019年10月11日下午2:40:02
 */
@SpringBootApplication(scanBasePackages = { "com.basic", "com.oceankeeper" })
@EnableSwagger2
public class OceankeeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(OceankeeperApplication.class, args);
	}
}