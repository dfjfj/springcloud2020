package com.atguigu.cloud.dao;

import com.atguigu.cloud.dao.entity.PaymentDO;
import com.atguigu.cloud.dao.mapper.PaymentMapper;
import com.atguigu.cloud.service.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 数据处理层
 */
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public int insert(PaymentDO paymentDO) {
        return paymentMapper.insert(paymentDO);
    }

    @Override
    public PaymentDO getById(long id) {
        return paymentMapper.selectById(id);
    }
}
