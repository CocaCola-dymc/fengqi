package com.dymc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.dymc.mapper")
public class JavaServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(JavaServiceApplication.class, args);
	}

}
