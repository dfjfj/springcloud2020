package com.atguigu.cloud.service;

import cn.hutool.core.lang.Snowflake;
import com.atguigu.cloud.api.dto.OrderDTO;
import com.atguigu.cloud.api.dto.PaymentFeignClient;
import com.atguigu.cloud.dao.entity.OrderDO;
import com.atguigu.cloud.dao.mapper.OrderMapper;
import com.atguigu.cloud.util.BeanConvertUtil;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private PaymentFeignClient paymentFeignClient;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private Snowflake snowflake;

    @GlobalTransactional(rollbackFor = Exception.class, name = "tx-order-payment")
    public OrderDTO createOrder(OrderDTO orderDTO) {
        long id = snowflake.nextId();
        OrderDO orderDO = BeanConvertUtil.convertBean(orderDTO, OrderDO.class);
        orderDO.setId(id);
        orderMapper.insert(orderDO);
        orderDTO.setId(String.valueOf(id));
        paymentFeignClient.decrease(orderDTO.getPayment());
        return orderDTO;
    }
}
