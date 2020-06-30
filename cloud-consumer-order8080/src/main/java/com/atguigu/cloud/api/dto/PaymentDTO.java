package com.atguigu.cloud.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaymentDTO implements Serializable {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 支付流水号
     */
    private String serial;
}
