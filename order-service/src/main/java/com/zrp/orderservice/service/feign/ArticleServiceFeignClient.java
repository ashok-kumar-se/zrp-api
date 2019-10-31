package com.zrp.orderservice.service.feign;

import com.zrp.orderservice.dto.ArticleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "article-service", url = "http://localhost:9002/api/v1")
public interface ArticleServiceFeignClient {
    @PostMapping(value = "/article", produces = "application/json")
    List<ArticleDto> getArticles(@RequestBody List<String> articleIds);
}
