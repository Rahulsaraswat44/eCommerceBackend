package com.rahulsaraswat.OrderService.exception;

import com.rahulsaraswat.OrderService.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomOrderException.class)
    public ResponseEntity<ErrorResponse> handleProductExceptionHandler(CustomOrderException e) {
        return new ResponseEntity<>(new ErrorResponse().builder()
                .errorCode(e.getErrorCode())
                .errorMessage(e.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }
}
