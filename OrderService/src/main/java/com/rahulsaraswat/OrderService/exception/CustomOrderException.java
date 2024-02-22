package com.rahulsaraswat.OrderService.exception;

import lombok.Data;

@Data
public class CustomOrderException extends RuntimeException{
    private String errorCode;
    private int status;

    public CustomOrderException(String errorMessage, String errorCode, int status) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.status = status;
    }
}
