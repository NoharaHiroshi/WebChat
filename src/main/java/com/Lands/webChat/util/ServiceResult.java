package com.Lands.webChat.util;
import java.io.Serializable;

public class ServiceResult<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public static <T> ServiceResult<T> success(T data) {
        ServiceResult<T> result = new ServiceResult<>();
        result.code = 0;
        result.msg = "";
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

}
