package com.pravat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestProj04MiniProjectOfMvcAppConsumerAppApplication {

	@Bean(name = "template")
	public RestTemplate createRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(RestProj04MiniProjectOfMvcAppConsumerAppApplication.class, args);
	}

}
