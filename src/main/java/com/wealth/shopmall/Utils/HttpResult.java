package com.wealth.shopmall.Utils;

import java.io.Serializable;
import java.util.List;

/**
 * 网络请求响应
 *
 * @param <T>
 */
public class HttpResult<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 描述信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public HttpResult() {
    }

    public HttpResult(Integer code) {
        super();
        this.code = code;
    }

    public HttpResult(String message) {
        super();
        this.message = message;
    }

    public HttpResult(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public HttpResult(Throwable e) {
        super();
        this.message = e.getMessage();
    }

    public HttpResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
