package com.atguigu.cloud.controller.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderVO implements Serializable {

    /**
     * 数据库表id, 雪花算法生成
     */
    private String id;

    /**
     * 订单号, 以TM开头(随意写的)
     */
    private String orderNo;
}
