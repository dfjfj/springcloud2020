package com.atguigu.cloud.controller;


import com.atguigu.cloud.api.dto.OrderDTO;
import com.atguigu.cloud.common.Response;
import com.atguigu.cloud.controller.vo.OrderVO;
import com.atguigu.cloud.service.OrderService;
import com.atguigu.cloud.util.BeanConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 测试seata分布式事务
     * 在创建订单时, 要求支付成功, 否则不创建订单
     */
    @PostMapping("/create")
    public Response<OrderVO> createOrder(@RequestBody OrderDTO order) {
        OrderDTO dto = orderService.createOrder(order);
        return Response.success(BeanConvertUtil.convertBean(dto, OrderVO.class));
    }
}
