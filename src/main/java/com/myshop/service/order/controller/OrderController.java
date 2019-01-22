package com.myshop.service.order.controller;

import com.myshop.service.order.exception.OrderServiceException;
import com.myshop.service.order.model.order.Order;
import com.myshop.service.order.model.response.OrderResponse;
import com.myshop.service.order.model.response.ResponseStatus;
import com.myshop.service.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        try {
            OrderResponse orderResponse = OrderResponse.builder()
                    .status(ResponseStatus.SUCCESS)
                    .data(orderService.getOrder(id))
                    .build();
            return new ResponseEntity<>(orderResponse, HttpStatus.OK);
        } catch (OrderServiceException e) {
            OrderResponse orderResponse = OrderResponse.builder()
                    .status(ResponseStatus.ERROR)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(orderResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> createOrder(@RequestBody Order order) {
        try {
            OrderResponse orderResponse = OrderResponse.builder()
                    .status(ResponseStatus.SUCCESS)
                    .data(orderService.createOrder(order))
                    .build();
            return new ResponseEntity<>(orderResponse, HttpStatus.OK);
        } catch (OrderServiceException e) {
            OrderResponse orderResponse = OrderResponse.builder()
                    .status(ResponseStatus.ERROR)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(orderResponse, HttpStatus.NOT_FOUND);
        }
    }
}
