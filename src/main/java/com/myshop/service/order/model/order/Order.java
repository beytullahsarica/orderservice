package com.myshop.service.order.model.order;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private Integer quantity;

    @Getter @Setter
    private Long customerId;

    @Getter @Setter
    private Long productId;

    @Getter @Setter
    private Date orderDate;

    @Getter @Setter
    private Double price;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private OrderStatus status;
}
