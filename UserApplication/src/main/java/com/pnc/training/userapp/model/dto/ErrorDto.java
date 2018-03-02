package com.pnc.training.userapp.model.dto;

/**
 * Created by PL64640 on 8/17/2017.
 */
public class ErrorDto<T> {

    private int errorCode;
    private T errorMessage = (T) "";

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public T getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(T errorMessage) {
        this.errorMessage = errorMessage;
    }
}
