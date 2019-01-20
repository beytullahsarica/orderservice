package com.myshop.service.order.repository;


import com.myshop.service.order.model.order.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
