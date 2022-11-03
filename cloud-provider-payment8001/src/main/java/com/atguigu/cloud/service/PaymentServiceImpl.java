package com.atguigu.cloud.service;

import com.atguigu.cloud.api.PaymentService;
import com.atguigu.cloud.api.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务处理层
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public int add(PaymentDTO paymentDTO) {
        return paymentRepository.insert(paymentDTO);
    }

    @Override
    public PaymentDTO getById(long id) {
        return paymentRepository.getById(id);

    }
}
