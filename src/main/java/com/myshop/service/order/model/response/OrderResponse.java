package com.myshop.service.order.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderResponse {

    private ResponseStatus status;
    private String message;
    private Object data;
}
