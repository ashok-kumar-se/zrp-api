package com.zrp.orderservice.service;

import  static org.mockito.Mockito.*;

import com.zrp.orderservice.dto.OrderDto;
import com.zrp.orderservice.repository.OrderArticleRepository;
import com.zrp.orderservice.repository.OrderRepository;
import com.zrp.orderservice.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class OrderServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderArticleRepository orderArticleRepository;

    @InjectMocks
    OrderServiceImpl orderServiceImpl;

    private static final String orderStatus = "Open";
    private static final String webOrderId = "IE025925669";
    private static final String storeId = "001";

    List<OrderDto> orders = Arrays.asList(
            new OrderDto("IE025925670",3,"13:22:00")
    );

    @Test
    public void getOrdersByOrderStatusTest() {
        orderServiceImpl.getOrdersByOrderStatus(orderStatus);
        verify(orderRepository, times(1)).findByOrderStatus(orderStatus);
    }

    @Test
    public void getArticlesByWebOrderIdTest() {
        orderServiceImpl.getArticlesByWebOrderId(webOrderId, storeId);
        verify(orderArticleRepository, times(1)).findByWebOrderId(webOrderId);
    }

}