package com.atguigu.cloud.controller;


import com.atguigu.cloud.api.PaymentService;
import com.atguigu.cloud.api.dto.PaymentDTO;
import com.atguigu.cloud.common.Response;
import com.atguigu.cloud.controller.vo.PaymentVO;
import com.atguigu.cloud.util.BeanConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * 支付controller
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;


    @GetMapping("/test")
    public String testConnection() {
        return "connect payment-service success! serverPort: " + serverPort;
    }

    @GetMapping("/{id}")
    public Response<PaymentVO> getPaymentById(@PathVariable("id") Long id) {
        PaymentDTO paymentDTO = paymentService.getById(id);
        PaymentVO paymentVO = BeanConvertUtil.convertBean(paymentDTO, PaymentVO.class);
        return new Response<>(1, 20000, "serverPort: " + serverPort + "===> 成功", paymentVO);
    }

    @PostMapping
    public Response<PaymentVO> generatePaymentInfo(@RequestBody PaymentDTO paymentDTO) {
        int count = paymentService.add(paymentDTO);
        return count > 0 ? new Response<>(1, 20000, "serverPort: " + serverPort + "===> 成功", null) :
                Response.fail(50001, "serverPort: " + serverPort + "===> 服务端异常");
    }
}
