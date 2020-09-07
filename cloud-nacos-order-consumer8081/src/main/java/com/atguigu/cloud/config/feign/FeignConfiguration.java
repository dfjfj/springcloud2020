package com.atguigu.cloud.config.feign;

import com.atguigu.cloud.service.feign.fallback.EchoFeignClientFallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public EchoFeignClientFallback echoFeignClientFallback() {
        return new EchoFeignClientFallback();
    }
}
