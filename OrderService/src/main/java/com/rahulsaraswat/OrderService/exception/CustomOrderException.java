package com.rahulsaraswat.OrderService.exception;

public class CustomOrderException extends RuntimeException{
    private String errorCode;
    private int status;

    CustomOrderException(String errorCode, String errorMessage, int status) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.status = status;
    }
}
