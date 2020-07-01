package com.atguigu.cloud.controller;


import com.atguigu.cloud.api.PaymentFeignClient;
import com.atguigu.cloud.api.dto.PaymentDTO;
import com.atguigu.cloud.common.Response;
import com.atguigu.cloud.controller.vo.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
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

    @GetMapping("/test")
    public String testConnection() {
        return "connect order-service success!";
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
