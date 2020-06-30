package com.atguigu.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OrderConfiguration {

    public static final String PAYMENT_URL = "http://localhost:8001/payment";

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(PAYMENT_URL).build();
        //return WebClient.create();
    }
}
