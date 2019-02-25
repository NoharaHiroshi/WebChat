package com.Lands.webChat.util;
import com.google.gson.Gson;

import java.io.Serializable;

public class ServiceResult<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public ServiceResult() {

    }

    public static <T> ServiceResult<T> success(T data) {
        ServiceResult<T> result = new ServiceResult<>();
        result.code = 0;
        result.msg = "success";
        result.data = data;
        return result;
    }

    public static <T> ServiceResult<T> success(String msg) {
        ServiceResult<T> result = new ServiceResult<>();
        result.code = 0;
        result.msg = msg;
        result.data = null;
        return result;
    }

    public static <T> ServiceResult<T> success(T data, String msg) {
        ServiceResult<T> result = new ServiceResult<>();
        result.code = 0;
        result.msg = msg;
        result.data = data;
        return result;
    }

    public static <T> ServiceResult<T> failure(int code, String msg) {
        ServiceResult<T> result = new ServiceResult<>();
        result.code = code;
        result.msg = msg;
        result.data = null;
        return result;
    }

    // 如果没有下列方法就会报 No converter found for return value of type 不知道为什么，研究一下
    public boolean hasResult() {
        return data != null;
    }

    public T getResult() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return msg;
    }
}
