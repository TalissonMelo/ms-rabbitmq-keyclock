package com.talissonmelo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableRabbit
@Slf4j
public class MsCartaoApplication {

	public static void main(String[] args) {
		log.info("Informação {}" , "LOG INFO");
		log.error("Error {}", "LOG ERROR");
		log.warn("Aviso {} ", "LOG WARN");
		SpringApplication.run(MsCartaoApplication.class, args);
	}

}
