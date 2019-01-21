package com.myshop.service.order.service;

import com.myshop.service.order.config.MessageResolver;
import com.myshop.service.order.exception.OrderServiceException;
import com.myshop.service.order.model.order.Order;
import com.myshop.service.order.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class OrderServiceTest {

    @TestConfiguration
    static class OrderServiceTestContextConfiguration {

        @Bean
        public OrderService orderService() {
            return new OrderService();
        }
    }

    @Autowired
    private OrderService orderService;

    @MockBean
    private MessageResolver messageResolver;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void testOrderServiceGetOrder() throws OrderServiceException {
        Order order = new Order();
        order.setProductId(111L);
        order.setQuantity(2);

        given(orderRepository.findById(any(Long.class))).willReturn(Optional.of(order));
        Order returnedOrder = orderService.getOrder(1L);
        assertEquals(returnedOrder.getQuantity(), order.getQuantity());
    }

    @Test(expected = OrderServiceException.class)
    public void testOrderServiceOrderNotFound() throws OrderServiceException {
        given(orderRepository.findById(any(Long.class))).willReturn(Optional.empty());

        Order returnedOrder = orderService.getOrder(1L);
    }

    @Test
    public void testOrderServiceCreateOrder() throws OrderServiceException {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY,1);

        Order order = new Order();
        order.setProductId(111L);
        order.setQuantity(1);
        order.setOrderDate(cal.getTime());

        given(orderRepository.save(any(Order.class))).willReturn(order);
        Order returnedOrder = orderService.createOrder(order);
        assertEquals(returnedOrder,order);
    }

    @Test(expected = OrderServiceException.class)
    public void testOrderServiceCreateOrderFail() throws OrderServiceException {
        Calendar cal = Calendar.getInstance();
        Order order = new Order();
        order.setQuantity(0);
        order.setOrderDate(cal.getTime());

        given(orderRepository.save(any(Order.class))).willReturn(order);
        Order returnedOrder = orderService.createOrder(order);
    }
}
