package com.atguigu.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableConfigurationProperties(WebEndpointProperties.class)
@Configuration(proxyBeanMethods = false)
public class ActuatorSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private WebEndpointProperties webEndpointProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests((requests) ->
//                requests.anyRequest().permitAll());
        String basePath = webEndpointProperties.getBasePath();
        String pattern = "/\\/" + basePath + "($|\\/.*)/";
        http.regexMatcher(pattern).authorizeRequests((requests) ->
                requests.anyRequest().permitAll());
        http.httpBasic();
    }

}