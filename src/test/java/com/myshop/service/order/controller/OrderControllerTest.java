package com.myshop.service.order.controller;

import com.myshop.service.order.exception.OrderServiceException;
import com.myshop.service.order.model.order.Order;
import com.myshop.service.order.model.response.OrderResponse;
import com.myshop.service.order.model.response.ResponseStatus;
import com.myshop.service.order.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderService orderService;


    @Test
    public void testOrderControllerGetOrder() {
        Order order = new Order();
        order.setProductId(111L);
        order.setQuantity(2);

        try {
            given(orderService.getOrder(any(Long.class))).willReturn(order);
        } catch (OrderServiceException e) {
            e.printStackTrace();
        }

        OrderResponse returnedOrder = orderController.getOrder(1L).getBody();
        assertEquals(returnedOrder.getStatus(), ResponseStatus.SUCCESS);
        assertEquals(((Order)returnedOrder.getData()).getQuantity(), order.getQuantity());
    }

    @Test
    public void testOrderControllerGetOrderNullData() {
        Order order = new Order();
        order.setQuantity(2);

        try {
            given(orderService.getOrder(1L)).willReturn(null);
        } catch (OrderServiceException e) {
            e.printStackTrace();
        }

        OrderResponse returnedOrder = orderController.getOrder(1L).getBody();
        assertEquals(null, returnedOrder.getData());
    }

    @Test
    public void testOrderControllerGetOrderFail() {
        try {
            given(orderService.getOrder(any(Long.class))).willThrow(OrderServiceException.class);
        } catch (OrderServiceException e) {
            e.printStackTrace();
        }

        OrderResponse returnedOrder = orderController.getOrder(1L).getBody();
        assertEquals(ResponseStatus.ERROR, returnedOrder.getStatus());
    }

    @Test
    public void testOrderControllerCreateOrder() {
        Order order = new Order();
        order.setProductId(111L);
        order.setQuantity(2);

        try {
            given(orderService.createOrder(any(Order.class))).willReturn(order);
        } catch (OrderServiceException e) {
            e.printStackTrace();
        }

        OrderResponse returnedOrder = orderController.createOrder(order).getBody();
        assertEquals(returnedOrder.getData(), order);
    }

    @Test
    public void testOrderControllerCreateOrderFail() {
        try {
            given(orderService.createOrder(any(Order.class))).willThrow(OrderServiceException.class);
        } catch (OrderServiceException e) {
            e.printStackTrace();
        }

        OrderResponse returnedOrder = orderController.createOrder(new Order()).getBody();
        assertEquals(ResponseStatus.ERROR, returnedOrder.getStatus());
    }
}
