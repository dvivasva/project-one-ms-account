package com.microservice.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@EnableEurekaClient
@SpringBootApplication
public class ProjectOneMsAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectOneMsAccountApplication.class, args);
	}

}
