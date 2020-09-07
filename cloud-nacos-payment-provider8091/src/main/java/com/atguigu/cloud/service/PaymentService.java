package com.atguigu.cloud.service;

import com.atguigu.cloud.dao.entity.PaymentDO;
import com.atguigu.cloud.dao.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    public boolean decrease(BigDecimal payment) {
        PaymentDO paymentDO = new PaymentDO();
        paymentDO.setSerial(payment.toString());
        throw new RuntimeException("payment模拟运行时异常");
        // return paymentMapper.insert(paymentDO) > 0;
    }
}
