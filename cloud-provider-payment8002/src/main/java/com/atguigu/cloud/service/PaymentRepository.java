package com.atguigu.cloud.service;

import com.atguigu.cloud.dao.entity.PaymentDO;

public interface PaymentRepository {

    int insert(PaymentDO paymentDO);

    PaymentDO getById(long id);
}
