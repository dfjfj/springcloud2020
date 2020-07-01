package com.atguigu.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OrderConfiguration {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE/payment";
    @Autowired
    private ReactorLoadBalancerExchangeFilterFunction lbFunction;

    /**
     * WebClient负载均衡 方式一
     * @return WebClient
     */
    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(PAYMENT_URL).filter(lbFunction).build();
        //return WebClient.create();
    }

    /**
     * WebClient负载均衡 方式二
     * @return WebClient.Builder
     */
    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder().baseUrl(PAYMENT_URL);
    }
}
