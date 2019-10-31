package com.zrp.orderservice.service.impl;

import com.zrp.orderservice.dto.ArticleDto;
import com.zrp.orderservice.dto.ArticleStockDto;
import com.zrp.orderservice.dto.OrderArticleDto;
import com.zrp.orderservice.dto.OrderDto;
import com.zrp.orderservice.entity.Order;
import com.zrp.orderservice.entity.OrderArticle;
import com.zrp.orderservice.repository.OrderArticleRepository;
import com.zrp.orderservice.repository.OrderRepository;
import com.zrp.orderservice.service.OrderService;
import com.zrp.orderservice.service.feign.ArticleServiceClient;
import com.zrp.orderservice.service.feign.StockServiceClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderArticleRepository orderArticleRepository;

    @Autowired
    private StockServiceClient stockServiceClient;

    @Autowired
    private ArticleServiceClient articleServiceClient;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Fetch all the orders for a given order status
     *
     * @param orderStatus
     * @return
     */
    @Override
    public List<OrderDto> getOrdersByOrderStatus(String orderStatus) {
        List<Order> orders = orderRepository.findByOrderStatus(orderStatus);
        if (!orders.isEmpty()) {
            return orders
                    .stream()
                    .map(order -> modelMapper.map(order, OrderDto.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * Fetch all the articles for a given web order id
     *
     * @param webOrderId
     * @return
     */
    @Override
    public List<OrderArticleDto> getArticlesByWebOrderId(String webOrderId, String storeId) {

        final List<OrderArticle> orderArticles = orderArticleRepository.findByWebOrderId(webOrderId);

        List<OrderArticleDto> orderArticleDtoList;

        if (!orderArticles.isEmpty()) {

            orderArticleDtoList = mapOrderArticles(orderArticles);

            final List<String> articles = getArticleIdsFromOrder(orderArticleDtoList);

            final List<ArticleDto> articleResponse = articleServiceClient.getArticles(articles);

            if (!articleResponse.isEmpty()) {

                orderArticleDtoList.forEach(orderArticle -> {

                    Optional<ArticleDto> filter = articleResponse.stream()
                            .filter(data -> orderArticle.getArticleId().equals(data.getArticleId()))
                            .findFirst();

                    if (filter.isPresent()) {

                        orderArticle.setArticle(filter.get());

                        Optional<ArticleStockDto> articleStockResponse = stockServiceClient.getArticleStock(orderArticle.getArticleId(), storeId, "0001");

                        if (articleStockResponse.isPresent()) {
                            Integer quantity = articleStockResponse.get().getStockQty();
                            orderArticle.setArticleStockQty(quantity);
                        }

                    }
                });
            }

            return orderArticleDtoList;
        }
        return Collections.emptyList();
    }

    @SuppressWarnings("unused")
    private List<String> getArticleIdsFromOrder(List<OrderArticleDto> orderArticleDtoList) {
        return orderArticleDtoList.stream()
                .map(article -> modelMapper.map(article.getArticleId(), String.class))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unused")
    private List<OrderArticleDto> mapOrderArticles(List<OrderArticle> orderArticles) {
        return orderArticles
                .stream()
                .map(article -> modelMapper.map(article, OrderArticleDto.class))
                .collect(Collectors.toList());
    }

}
