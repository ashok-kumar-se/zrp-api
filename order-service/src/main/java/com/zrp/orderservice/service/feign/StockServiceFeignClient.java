package com.zrp.orderservice.service.feign;

import com.zrp.orderservice.dto.ArticleStockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "stock-service", url = "http://localhost:9003")
public interface StockServiceFeignClient {

    @GetMapping(value = "/stock/{articleId}/{storeId}/{storeLoc}", produces = "application/json")
    Optional<ArticleStockDto> getArticleStock(@PathVariable(value = "articleId") String articleId, @PathVariable(value = "storeId") String storeId, @PathVariable(value = "storeLoc") String storeLoc);
}
