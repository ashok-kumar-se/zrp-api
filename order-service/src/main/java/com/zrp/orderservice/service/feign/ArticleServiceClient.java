package com.zrp.orderservice.service.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zrp.orderservice.dto.ArticleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ArticleServiceClient {

    @Autowired
    private ArticleServiceFeignClient articleServiceFeignClient;

    @HystrixCommand(fallbackMethod = "getDefaultArticles", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public List<ArticleDto> getArticlesByArticleIds(List<String> articleIds) {
        return this.articleServiceFeignClient.getArticlesByArticleIds(articleIds);
    }

    @SuppressWarnings("unused")
    private List<ArticleDto> getDefaultArticles(List<String> articleIds) {
        log.info("Returning default stock levels");
        return new ArrayList<>();
    }
}
