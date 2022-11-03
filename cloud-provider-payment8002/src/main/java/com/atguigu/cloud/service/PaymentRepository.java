package com.atguigu.cloud.service;

import com.atguigu.cloud.api.dto.PaymentDTO;

public interface PaymentRepository {

    int insert(PaymentDTO paymentDTO);

    PaymentDTO getById(long id);
}
