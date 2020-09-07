package com.atguigu.cloud.service.feign;

import com.atguigu.cloud.config.feign.FeignConfiguration;
import com.atguigu.cloud.service.feign.fallback.EchoFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// TODO openFeign和sentinel整合时 primary属性没有生效.必须手动添加@Primary注解
@Primary
@FeignClient(name = "payment-provider", fallback = EchoFeignClientFallback.class, configuration = FeignConfiguration.class, contextId = "echoFeignClient")
public interface EchoFeignClient {
    @GetMapping(value = "echo/{str}")
    String echo(@PathVariable("str") String str);
}

