package com.zrp.orderservice.controller;

import  static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.zrp.orderservice.dto.OrderArticleDto;
import com.zrp.orderservice.dto.OrderDto;
import com.zrp.orderservice.service.OrderService;
import org.junit.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(OrderControllerTest.class);

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;

    @Mock
    OrderDto orderDto;

    @Mock
    OrderArticleDto orderArticleDto;

    private static final String orderStatus = "Picked";
    private static final String webOrderId = "IE025925669";
    private static final String storeId = "001";

    @Test
    public void getOrdersByOrderStatusTest() {
        List<OrderDto> orders = new ArrayList<>();
        orders.add(orderDto);
        logger.debug("Method: getOrdersByOrderStatus(), Parameters: orderStatus - {}", orderStatus);
        when(orderService.getOrdersByOrderStatus(orderStatus)).thenReturn(orders);
        assertEquals(orderController.getOrdersByOrderStatus(orderStatus).getBody().size(), orders.size());
    }

    @Test
    public void getArticlesByWebOrderIdTest() {
        List<OrderArticleDto> articles = new ArrayList<>();
        articles.add(orderArticleDto);
        logger.debug("Method: getArticlesByWebOrderId(), Parameters: webOrderId - {}, storeId - {}", webOrderId, storeId);
        when(orderService.getArticlesByWebOrderId(webOrderId, storeId)).thenReturn(articles);
        assertEquals(orderController.getArticlesByWebOrderId(webOrderId, storeId).getBody().size(), articles.size());
    }

}