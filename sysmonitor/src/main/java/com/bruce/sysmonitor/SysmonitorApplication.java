package com.bruce.sysmonitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.bruce.sysmonitor.mapper")
public class SysmonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SysmonitorApplication.class, args);
	}

}
