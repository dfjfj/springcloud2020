package com.atguigu.cloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.cloud.config.sentinel.BlockAndFallBackUtil;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TestService {

    private final AtomicInteger count = new AtomicInteger(0);

    // 声明sentinel资源
    @SentinelResource(value = "sayHello", blockHandlerClass = {BlockAndFallBackUtil.class}, blockHandler = "exceptionHandler",
            fallbackClass = {BlockAndFallBackUtil.class}, fallback = "helloFallback")
    public String sayHello(String name) {
        int currentCount = count.addAndGet(1);
        System.out.println("count: " + currentCount);
        if (currentCount % 2 != 0) {
            throw new RuntimeException("本次不是偶数, 抛出异常...sayHello");
        }
        return "Hello, " + name;
    }
}