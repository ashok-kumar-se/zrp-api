package com.zrp.orderservice.controller;

import  static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.zrp.orderservice.dto.OrderArticleDto;
import com.zrp.orderservice.dto.OrderDto;
import com.zrp.orderservice.service.OrderService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class OrderControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

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