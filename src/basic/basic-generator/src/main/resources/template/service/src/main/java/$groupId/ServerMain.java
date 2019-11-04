package ${project.groupId};

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
@SpringBootApplication(scanBasePackages= {"com.basic","${project.groupId}"})
@EnableDubbo
@MapperScan("com.basic")
public class ServerMain { 
	public static void main(String[] args) {
		SpringApplication.run(ServerMain.class, args);
	}
}