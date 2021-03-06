package com.zrp.orderservice.controller;

import com.zrp.orderservice.dto.OrderArticleDto;
import com.zrp.orderservice.dto.OrderDto;
import com.zrp.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @GetMapping("/status/{orderStatus}")
    public ResponseEntity<List<OrderDto>> getOrdersByOrderStatus(@PathVariable(value = "orderStatus") String orderStatus) {
        logger.debug("Method: getOrdersByOrderStatus(), QueryParams: orderStatus - {}", orderStatus);
        return new ResponseEntity<List<OrderDto>>(orderService.getOrdersByOrderStatus(orderStatus), HttpStatus.OK);
    }

    @GetMapping("/articles/{webOrderId}/{storeId}")
    public ResponseEntity<List<OrderArticleDto>> getArticlesByWebOrderId(@PathVariable(value = "webOrderId") String webOrderId, @PathVariable(value = "storeId") String storeId) {
        logger.debug("Method: getArticlesByWebOrderId(), QueryParams: webOrderId - {}, storeId - {}", webOrderId, storeId);
        return new ResponseEntity<List<OrderArticleDto>>(orderService.getArticlesByWebOrderId(webOrderId, storeId), HttpStatus.OK);
    }
}
