package com.example.tianyi.iphoneassist.common.exception;

/**
 * Created by Tianyi on 2017/11/16.
 */

public class ApiException extends BaseException {

    private int code;
    private String message;

    public ApiException() {
    }

    public ApiException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
