package com.ssafy.top;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TopApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopApplication.class, args);
	}

}
