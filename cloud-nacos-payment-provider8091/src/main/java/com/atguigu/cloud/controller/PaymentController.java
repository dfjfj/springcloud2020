package com.atguigu.cloud.controller;

import com.atguigu.cloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * 扣减金额
     * @return 成功与否
     */
    @PutMapping("/decrease")
    public Boolean decrease(BigDecimal payment) {
        return paymentService.decrease(payment);
    }
}
