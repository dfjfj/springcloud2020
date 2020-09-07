package com.atguigu.cloud.config;

import cn.hutool.core.lang.Snowflake;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfiguration {

    // 使用了Ribbon负载均衡(基于服务名)
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 雪花算法生成对象, 需保证是单例
     */
    @Bean
    public Snowflake snowflake() {
        long workerId = 1;
        long datacenterId = 1;
        return new Snowflake(workerId, datacenterId);
    }
}
