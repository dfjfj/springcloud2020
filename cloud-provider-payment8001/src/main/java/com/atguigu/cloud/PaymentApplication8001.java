package com.atguigu.cloud;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * EnableDiscoveryClient 可以不标注此标签,系统会自动根据classpath上的jar包依赖推断
 */
@EnableDiscoveryClient
@MapperScan("com.atguigu.cloud.dao.mapper")
@SpringBootApplication
@Slf4j
public class PaymentApplication8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication8001.class, args);
        log.info("payment application started...");
    }
}
