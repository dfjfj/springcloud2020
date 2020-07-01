package com.atguigu.cloud.service;

import com.atguigu.cloud.api.PaymentService;
import com.atguigu.cloud.api.dto.PaymentDTO;
import com.atguigu.cloud.dao.entity.PaymentDO;
import com.atguigu.cloud.util.BeanConvertUtil;
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
        PaymentDO paymentDO = BeanConvertUtil.convertBean(paymentDTO, PaymentDO.class);
        return paymentRepository.insert(paymentDO);
    }

    @Override
    public PaymentDTO getById(long id) {
        PaymentDO paymentDO = paymentRepository.getById(id);
        return BeanConvertUtil.convertBean(paymentDO, PaymentDTO.class);
    }
}
