package com.atguigu.cloud.service.feign.fallback;


import com.atguigu.cloud.service.feign.EchoFeignClient;
import org.springframework.web.bind.annotation.PathVariable;

public class EchoFeignClientFallback implements EchoFeignClient {
    @Override
    public String echo(@PathVariable("str") String str) {
        return "EchoServiceFallback: " + str;
    }
}