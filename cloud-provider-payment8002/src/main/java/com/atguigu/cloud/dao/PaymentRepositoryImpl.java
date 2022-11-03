package com.atguigu.cloud.dao;

import com.atguigu.cloud.api.dto.PaymentDTO;
import com.atguigu.cloud.dao.entity.PaymentDO;
import com.atguigu.cloud.dao.mapper.PaymentMapper;
import com.atguigu.cloud.service.PaymentRepository;
import com.atguigu.cloud.util.BeanConvertUtil;
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
    public int insert(PaymentDTO paymentDTO) {
        PaymentDO paymentDO = BeanConvertUtil.convertBean(paymentDTO, PaymentDO.class);
        return paymentMapper.insert(paymentDO);
    }

    @Override
    public PaymentDTO getById(long id) {
        PaymentDO paymentDO = paymentMapper.selectById(id);
        return BeanConvertUtil.convertBean(paymentDO, PaymentDTO.class);
    }
}
