package com.myshop.service.order.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public OrderResponse handleException(DataIntegrityViolationException e) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setStatusCode(ResponseStatus.ERROR);
        orderResponse.setMessage(e.getCause().getMessage());
        return orderResponse;
    }
}
