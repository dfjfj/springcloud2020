package com.atguigu.cloud.api.dto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "cloud-payment-service", contextId = "paymentFeignClient", path = "payment")
public interface PaymentFeignClient {

    /**
     * 扣减金额
     * @param payment 订单金额
     */
    @PutMapping("/decrease")
    Boolean decrease(@RequestParam("payment") BigDecimal payment);
}
