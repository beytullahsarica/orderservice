package com.myshop.service.order.service;

import com.myshop.service.order.config.MessageResolver;
import com.myshop.service.order.exception.OrderServiceException;
import com.myshop.service.order.model.order.Order;
import com.myshop.service.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MessageResolver messageResolver;

    public Order getOrder(Long id) throws OrderServiceException {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            return order;
        } else {
            throw new OrderServiceException(messageResolver.getMessage("order_not_found"));
        }
    }

    public Order createOrder(Order order) throws OrderServiceException {
        if (order.getQuantity() > 0 && order.getOrderDate().compareTo(new Date()) > 0) {
            return orderRepository.save(order);
        } else {
            throw new OrderServiceException(messageResolver.getMessage("invalid_quantity_or_date_error"));
        }
    }
}
