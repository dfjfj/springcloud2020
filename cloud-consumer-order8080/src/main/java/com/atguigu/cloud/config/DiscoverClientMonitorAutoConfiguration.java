package com.atguigu.cloud.config;


import com.atguigu.cloud.actuator.DiscoverClientMonitorEndpoint;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.ConditionalOnBlockingDiscoveryEnabled;
import org.springframework.cloud.client.ConditionalOnDiscoveryEnabled;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(Endpoint.class)
@ConditionalOnDiscoveryEnabled
@ConditionalOnBlockingDiscoveryEnabled
public class DiscoverClientMonitorAutoConfiguration {


    @ConditionalOnAvailableEndpoint
    @Bean
    public DiscoverClientMonitorEndpoint discoverClientMonitorEndpoint() {
        return new DiscoverClientMonitorEndpoint();
    }

}
