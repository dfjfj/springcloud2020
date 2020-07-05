package com.atguigu.cloud.config;

import com.atguigu.cloud.filter.ratelimit.RemoteAddressKeyResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean(name = RemoteAddressKeyResolver.BEAN_NAME)
    @ConditionalOnClass(RateLimiter.class)
    public RemoteAddressKeyResolver remoteAddressKeyResolver() {
        return new RemoteAddressKeyResolver();
    }
}
