package com.atguigu.cloud.api;

import com.atguigu.cloud.api.dto.PaymentDTO;

public interface PaymentService {

    int add(PaymentDTO paymentDTO);

    PaymentDTO getById(long id);
}
