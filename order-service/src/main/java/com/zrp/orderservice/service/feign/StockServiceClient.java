package com.zrp.orderservice.service.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zrp.orderservice.dto.ArticleStockDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class StockServiceClient {

    @Autowired
    private StockServiceFeignClient stockServiceFeignClient;

    @HystrixCommand(fallbackMethod = "getDefaultArticleStock", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public Optional<ArticleStockDto> getStockByArticleIdAndStoreIdAndStoreLoc(String articleId, String storeId, String storeLoc) {
        return this.stockServiceFeignClient.getStockByArticleIdAndStoreIdAndStoreLoc(articleId, storeId, storeLoc);
    }

    @SuppressWarnings("unused")
    private Optional<ArticleStockDto> getDefaultArticleStock(String articleId, String storeId, String storeLoc) {
        log.info("Returning default stock levels");
        return Optional.empty();
    }

}
