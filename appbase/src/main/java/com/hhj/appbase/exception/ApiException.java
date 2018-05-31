package com.hhj.appbase.exception;

/**
 * Created by hhj on 2018/3/8.
 */

public class ApiException extends RuntimeException {
    private int code;
    private String msg;

    public ApiException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
