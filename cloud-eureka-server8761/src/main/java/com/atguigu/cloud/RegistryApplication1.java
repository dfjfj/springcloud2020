package com.atguigu.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;



@EnableEurekaServer
@SpringBootApplication
public class RegistryApplication1 {
    public static void main(String[] args) {
        SpringApplication.run(RegistryApplication1.class, args);
    }
}
