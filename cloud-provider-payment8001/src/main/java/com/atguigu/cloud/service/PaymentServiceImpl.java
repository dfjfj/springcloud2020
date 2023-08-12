package com.atguigu.cloud.service;

import com.atguigu.cloud.api.PaymentService;
import com.atguigu.cloud.api.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 业务处理层
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    @Override
    public int add(PaymentDTO paymentDTO) {
        return paymentRepository.insert(paymentDTO);
    }

    @Transactional
    @Override
    public PaymentDTO getById(long id) {
        return paymentRepository.getById(id);

    }
}
