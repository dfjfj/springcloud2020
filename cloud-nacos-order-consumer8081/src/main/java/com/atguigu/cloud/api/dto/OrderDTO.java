package com.atguigu.cloud.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderDTO implements Serializable {

    /**
     * TODO 使用雪花算法的id, 前后端传递时需要使用String类型, 防止前端转换为Number时导致精度损失
     */
    private String id;

    /**
     * 订单号, 以TM开头(随意写的)
     */
    private String orderNo;

    /**
     * 金额
     */
    private BigDecimal payment;
}
