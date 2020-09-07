package com.atguigu.cloud.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@TableName("tbl_order")
@Data
public class OrderDO implements Serializable {

    /**
     * 雪花算法id
     */
    @TableId(type = IdType.INPUT)
    private long id;

    /**
     * 订单号
     */
    private String orderNo;
}
