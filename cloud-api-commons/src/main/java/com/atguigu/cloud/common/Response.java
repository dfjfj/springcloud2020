package com.atguigu.cloud.common;


import lombok.Data;

/**
 * code: 响应状态码
 * 1: 成功 2: 失败 -1:未登录 -2:账号被挤下线
 * stateCode: 业务类型或系统错误类型状态码
 * 20000: 成功
 * 20001 ~ 49999: 业务状态码号段
 * 50001 ~ 69999: 系统错误状态码
 *
 * @param <T> data泛型
 */

@Data
public final class Response<T> {

    private int code;

    private int stateCode;

    private String message;

    private T data;

    public Response(int code, int stateCode, String message, T data) {
        this.code = code;
        this.stateCode = stateCode;
        this.message = message;
        this.data = data;
    }

    public Response(int code, int stateCode, String message) {
        this(code, stateCode, message, null);
    }

    /**
     * 无数据成功响应
     *
     * @param <T> data泛型
     * @return Response
     */
    public static <T> Response<T> success() {
        return new Response<>(1, 20000, "成功");
    }

    /**
     * 有数据成功响应
     *
     * @param data 响应数据
     * @param <T>  data泛型
     * @return Response
     */
    public static <T> Response<T> success(T data) {
        return new Response<>(1, 20000, "成功", data);
    }

    /**
     * 无数据固定失败状态响应
     *
     * @param <T> data泛型
     * @return Response
     */
    public static <T> Response<T> fail() {
        return new Response<>(2, 50001, "失败", null);
    }

    /**
     * 无数据指定失败状态和信息响应
     *
     * @param stateCode 失败状态码
     * @param message   失败信息
     * @param <T>       data泛型
     * @return Response
     */
    public static <T> Response<T> fail(int stateCode, String message) {
        return new Response<>(2, stateCode, message, null);
    }

}
