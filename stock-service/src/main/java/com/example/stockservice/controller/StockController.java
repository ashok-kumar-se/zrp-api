package com.example.stockservice.controller;

import com.example.stockservice.dto.ArticleStockDto;
import com.example.stockservice.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockService stockService;

    @GetMapping("/{articleId}/{storeId}/{storeLoc}")
    public ResponseEntity<ArticleStockDto> getStockByArticleIdAndStoreIdAndStoreLoc(@PathVariable(value = "articleId") String articleId, @PathVariable(value = "storeId") String storeId, @PathVariable(value = "storeLoc") String storeLoc) {
        logger.debug("Method: getStockByArticleIdAndStoreIdAndStoreLoc(), QueryParams: articleId - {}, storeId - {}, storeLoc - {}", articleId, storeId, storeLoc);
        return new ResponseEntity<ArticleStockDto>(stockService.getStockByArticleIdAndStoreIdAndStoreLoc(articleId, storeId, storeLoc), HttpStatus.OK);
    }


}
