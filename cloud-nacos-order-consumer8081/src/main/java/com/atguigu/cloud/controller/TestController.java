package com.atguigu.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.cloud.config.sentinel.BlockAndFallBackUtil;
import com.atguigu.cloud.service.TestService;
import com.atguigu.cloud.service.feign.EchoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    private final RestTemplate restTemplate;

    @Autowired
    private TestService service;

    @Autowired
    private EchoFeignClient echoFeignClient;

    @Autowired
    public TestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 测试nacos作为注册中心的使用
    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        return restTemplate.getForObject("http://payment-provider/echo/" + str, String.class);
    }

    @SentinelResource(value = "feignEcho", blockHandlerClass = {BlockAndFallBackUtil.class}, blockHandler = "exceptionHandler")
    @RequestMapping(value = "/feign/echo/{str}", method = RequestMethod.GET)
    public String echoByFeign(@PathVariable String str) {
        return echoFeignClient.echo(str);
    }


    @GetMapping(value = "/hello/{name}")
    public String apiHello(@PathVariable String name) {
        return service.sayHello(name);
    }

}