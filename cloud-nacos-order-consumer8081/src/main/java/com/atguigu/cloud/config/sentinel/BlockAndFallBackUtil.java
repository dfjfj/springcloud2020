package com.atguigu.cloud.config.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;

// 该类用于@SentinelResource中的服务治理, 包括服务降级和容错.
public class BlockAndFallBackUtil {

    // TODO Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数. 如果该方法不是在资源类中, 则必须是static类型
    public static String helloFallback(String name, Throwable throwable) {
        String exceptionMessage = throwable.getMessage();
        return name + "... helloFallback: " + exceptionMessage;
    }

    // TODO Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.如果该方法不是在资源类中, 则必须是static类型
    public static String exceptionHandler(String name, BlockException exception) {
        String exceptionMessage = exception.getClass().getCanonicalName();
        return name + "... exceptionHandler: " + exceptionMessage;
    }
}
