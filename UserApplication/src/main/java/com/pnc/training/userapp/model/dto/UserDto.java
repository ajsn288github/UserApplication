package com.pnc.training.userapp.model.dto;

/**
 * Created by PL64640 on 8/17/2017.
 */
public class UserDto<T> {

    public enum status {
        SUCCESS, FAIL, ERROR
    }

    private ErrorDto errorData = null;
    private T data = null;
    private String status;

    public ErrorDto getErrorData() {
        return errorData;
    }

    public void setErrorData(ErrorDto errorData) {
        this.errorData = errorData;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
