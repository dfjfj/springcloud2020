package com.atguigu.cloud.api;

import com.atguigu.cloud.api.dto.PaymentDTO;
import com.atguigu.cloud.common.Response;
import com.atguigu.cloud.config.FeignRequestInterceptor;
import com.atguigu.cloud.controller.vo.PaymentVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CLOUD-PAYMENT-SERVICE", path = "/payment", configuration = FeignRequestInterceptor.class)
public interface PaymentFeignClient {

    @GetMapping("/{id}")
    Response<PaymentVO> getPaymentById(@PathVariable("id") Long id);

    @PostMapping
    Response<PaymentVO> generatePaymentInfo(@RequestBody PaymentDTO paymentDTO);
}
