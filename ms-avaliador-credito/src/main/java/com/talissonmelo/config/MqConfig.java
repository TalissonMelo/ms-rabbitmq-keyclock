package com.talissonmelo.config;

import lombok.Value;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

    @Value("${mq.queues.emissao-cartoes}")
    private String fila;


    @Bean
    public Queue queue(){
        return new Queue(fila, true);
    }
}
