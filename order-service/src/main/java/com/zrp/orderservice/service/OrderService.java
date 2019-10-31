package com.zrp.orderservice.service;

import com.zrp.orderservice.dto.OrderArticleDto;
import com.zrp.orderservice.dto.OrderDto;

import java.util.List;

public interface OrderService {

   List<OrderDto> getOrdersByOrderStatus(String orderStatus);
   List<OrderArticleDto> getArticlesByWebOrderId(String webOrderId,String storeId);

}
