package com.atguigu.cloud.controller.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaymentVO implements Serializable {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 支付流水号
     */
    private String serial;
}
