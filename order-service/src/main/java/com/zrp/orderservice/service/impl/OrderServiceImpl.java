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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    // Initialize order repository
    @Autowired
    private OrderRepository orderRepository;

    // Initialize order article repository
    @Autowired
    private OrderArticleRepository orderArticleRepository;

    // Initialize stock service feign client
    @Autowired
    private StockServiceClient stockServiceClient;

    // Initialize article service feign client
    @Autowired
    private ArticleServiceClient articleServiceClient;

    // Initialize model mapping
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Fetch all the orders for a given order status
     *
     * @param orderStatus - string
     * @return List of type OrderDto
     */
    @Override
    public List<OrderDto> getOrdersByOrderStatus(String orderStatus) {
        logger.debug("Method: getOrdersByOrderStatus(), Params: orderStatus - {}", orderStatus);
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
     * First it fetches all the order article details for the given web order Id.
     * Then a function is called to extract list of article Id alone from the above fetched list.
     * Then it communicates with article service to fetch the article details
     * for the above fetched list of article Id.
     * Finally it communicates with stock service and fetch the stock details
     * for the above list of article details for the given store Id and location.
     *
     * @param webOrderId - string
     * @param storeId - string
     * @return List of type OrderArticleDto
     */
    @Override
    public List<OrderArticleDto> getArticlesByWebOrderId(String webOrderId, String storeId) {
        logger.debug("Method: getArticlesByWebOrderId(), Params: webOrderId - {}, storeId - {}", webOrderId, storeId);
        // fetches list of order article details for the given web order id
        final List<OrderArticle> orderArticles = orderArticleRepository.findByWebOrderId(webOrderId);

        List<OrderArticleDto> orderArticleDtoList;

        // check if list is not empty
        if (!orderArticles.isEmpty()) {

            // model mapping
            orderArticleDtoList = mapOrderArticles(orderArticles);

            // function that returns list of article Id
            final List<String> articleIds = getArticleIdsFromOrderArticleList(orderArticleDtoList);

            // fetches list of article details for the given list of article Id
            final List<ArticleDto> articleServiceClientResponse = articleServiceClient.getArticlesByArticleIds(articleIds);

            if (!articleServiceClientResponse.isEmpty()) {

                // loop for article details
                orderArticleDtoList.forEach(orderArticle -> {

                    // filter the current article in the loop
                    Optional<ArticleDto> filter = articleServiceClientResponse.stream()
                            .filter(data -> orderArticle.getArticleId().equals(data.getArticleId()))
                            .findFirst();

                    if (filter.isPresent()) {

                        orderArticle.setArticle(filter.get());

                        // fetches stock details for the current article in the loop, store Id and location
                        Optional<ArticleStockDto> stockServiceClientResponse =
                                stockServiceClient.getStockByArticleIdAndStoreIdAndStoreLoc(orderArticle.getArticleId(), storeId, "0001");

                        // set the stock qty for the current article in the loop
                        if (stockServiceClientResponse.isPresent()) {
                            int quantity = stockServiceClientResponse.get().getStockQty();
                            orderArticle.setArticleStockQty(quantity);
                        }

                    }
                });
            }

            return orderArticleDtoList;
        }
        return Collections.emptyList();
    }

    /**
     * accepts list of type OrderArticle and returns list of type OrderArticleDto
     * @param orderArticles - list of type OrderArticle
     * @return list of type OrderArticleDto
     */
    @SuppressWarnings("unused")
    private List<OrderArticleDto> mapOrderArticles(List<OrderArticle> orderArticles) {
        return orderArticles
                .stream()
                .map(article -> modelMapper.map(article, OrderArticleDto.class))
                .collect(Collectors.toList());
    }

    /**
     * accepts list of type OrderArticleDto and returns list of article Id
     * @param orderArticleDtoList - list of type OrderArticleDto
     * @return list of string(article Id)
     */
    @SuppressWarnings("unused")
    private List<String> getArticleIdsFromOrderArticleList(List<OrderArticleDto> orderArticleDtoList) {
        return orderArticleDtoList.stream()
                .map(article -> modelMapper.map(article.getArticleId(), String.class))
                .collect(Collectors.toList());
    }

}
