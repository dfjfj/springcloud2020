package com.atguigu.cloud.controller;


import com.atguigu.cloud.api.PaymentService;
import com.atguigu.cloud.api.dto.PaymentDTO;
import com.atguigu.cloud.common.Response;
import com.atguigu.cloud.controller.vo.PaymentVO;
import com.atguigu.cloud.support.util.BeanConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 支付controller
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/test")
    public String testConnection() {
        return "connect payment-service success!";
    }

    @GetMapping("/{id}")
    public Response<PaymentVO> getPaymentById(@PathVariable("id") Long id) {
        PaymentDTO paymentDTO = paymentService.getById(id);
        PaymentVO paymentVO = BeanConvertUtil.convertBean(paymentDTO, PaymentVO.class);
        return Response.success(paymentVO);
    }

    @PostMapping
    public Response<PaymentVO> generatePaymentInfo(@RequestBody PaymentDTO paymentDTO) {
        int count = paymentService.add(paymentDTO);
        return count > 0 ? Response.success() : Response.fail();
    }
}
