package com.springboot.springboot2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用响应结果封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePojo<T> {

    /** 状态码：200成功，400客户端错误，500服务器错误 */
    private Integer code;

    /** 提示信息 */
    private String message;

    /** 返回数据 */
    private T data;

    // ------------------------------------------------------------
    // ✅ 成功响应
    // ------------------------------------------------------------
    public static <T> ResponsePojo<T> success(T data) {
        return new ResponsePojo<>(200, "成功", data);
    }

    public static <T> ResponsePojo<T> success(T data, String message) {
        return new ResponsePojo<>(200, message, data);
    }

    // ------------------------------------------------------------
    // ✅ 客户端失败响应（如参数错误、数据不存在等）
    // ------------------------------------------------------------
    public static <T> ResponsePojo<T> fail(T data, String message) {
        return new ResponsePojo<>(400, message, data);
    }

    // ------------------------------------------------------------
    // ✅ 服务器异常错误（如插入失败、异常捕获）
    // ------------------------------------------------------------
    public static <T> ResponsePojo<T> error(String message) {
        return new ResponsePojo<>(500, message, null);
    }
}
