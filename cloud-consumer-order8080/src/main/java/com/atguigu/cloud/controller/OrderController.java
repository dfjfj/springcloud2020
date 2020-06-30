package com.atguigu.cloud.controller;


import com.atguigu.cloud.api.dto.PaymentDTO;
import com.atguigu.cloud.common.Response;
import com.atguigu.cloud.controller.vo.PaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private WebClient webClient;

    @GetMapping("/test")
    public String testConnection() {
        return "connect order-service success!";
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
        return webClient.post().contentType(MediaType.APPLICATION_JSON)
                .bodyValue(payment)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Response<PaymentVO>>() {
                }).block();
    }
}
