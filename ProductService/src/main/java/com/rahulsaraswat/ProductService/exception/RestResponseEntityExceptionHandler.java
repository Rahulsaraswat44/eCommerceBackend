package com.rahulsaraswat.ProductService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.rahulsaraswat.ProductService.model.ErrorResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductServiceCustomException.class)
    public ResponseEntity<ErrorResponse> handleProductExceptionHandler(ProductServiceCustomException e) {
        return new ResponseEntity<>(new ErrorResponse().builder()
                .errorCode(e.getErrorCode())
                .errorMessage(e.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }
}
