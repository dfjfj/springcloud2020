package com.atguigu.cloud.controller;


import com.atguigu.cloud.api.PaymentFeignClient;
import com.atguigu.cloud.api.dto.PaymentDTO;
import com.atguigu.cloud.common.Response;
import com.atguigu.cloud.controller.vo.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private WebClient webClient;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private PaymentFeignClient paymentFeignClient;
    @Autowired
    private Environment environment;

    @GetMapping("/test")
    public String testConnection() {
        return "connect order-service success!";
    }

    /**
     * 试验: 尝试从配置中心获取属性值
     * 结论: 如果更新了远程配置信息, 则需要手动调用 post /actuator/refresh端点, 抓取更新配置
     * warn: 有点遗憾的是: 对于list和map结构, 无法正确解析; 例如: 存入一个test-key[0]和test-key[1],识别后无法合并为test-key
     * @param key 配置中心存储的对应配置文件信息
     * @return response with value or fail message
     */
    @GetMapping("/env/{key}")
    public Response<String> getValueFromEnvironmentWithKey(@PathVariable("key") String key) {
        Assert.notNull(environment, "environment must not be null!");
        String[] activeProfiles = environment.getActiveProfiles();
        return environment.containsProperty(key) ? Response.success(environment.getProperty(key)) : Response.fail(50001, "Cannot get the property from environment where key is " + key +
                "! The activeProfiles is " + Arrays.toString(activeProfiles));
    }

    @GetMapping("/discovery/serverList")
    public Response<List<String>> getAllServiceIds() {
        List<String> services = discoveryClient.getServices();
        return Response.success(services);
    }

    @GetMapping("/discovery/{serviceId}")
    public Response<List<ServiceInstance>> getInstances(@PathVariable("serviceId") String serviceId) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        return Response.success(instances);
    }

    // 测试标注于controller层的缓存注解(该层注册将不会和service层共享缓存)
    @Cacheable(cacheNames = {"paymentInfo"})
    @GetMapping("/{id}")
    public Response<PaymentVO> getPaymentInfo(@PathVariable("id") Long id) {
        return webClient.get().uri("/{id}", id)
                .accept(MediaType.APPLICATION_JSON).retrieve()
                .bodyToMono(new ParameterizedTypeReference<Response<PaymentVO>>() {
                }).block(Duration.ofMillis(5000L));
    }


    @PostMapping("/create_payment")
    public Response<PaymentVO> createPayment(@RequestBody PaymentDTO payment) {
        return webClientBuilder.build().post().contentType(MediaType.APPLICATION_JSON)
                .bodyValue(payment)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Response<PaymentVO>>() {
                }).block();
    }

    @GetMapping("/by_feign/{id}")
    public Response<PaymentVO> getPaymentInfoByFeign(@PathVariable("id") Long id) {
        return paymentFeignClient.getPaymentById(id);
    }

    @PostMapping("/by_feign/create_payment")
    public Response<PaymentVO> createPaymentByFeign(@RequestBody PaymentDTO payment) {
        return paymentFeignClient.generatePaymentInfo(payment);
    }
}
