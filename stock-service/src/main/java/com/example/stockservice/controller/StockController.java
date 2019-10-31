package com.example.stockservice.controller;

import com.example.stockservice.dto.ArticleStockDto;
import com.example.stockservice.service.StockService;
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

    @Autowired
    private StockService stockService;

    @GetMapping("/{articleId}/{storeId}/{storeLoc}")
    public ResponseEntity<ArticleStockDto> getArticleStock(@PathVariable(value = "articleId") String articleId, @PathVariable(value = "storeId") String storeId, @PathVariable(value = "storeLoc") String storeLoc) {
        return new ResponseEntity<ArticleStockDto>(stockService.getArticleStock(articleId, storeId, storeLoc), HttpStatus.OK);
    }


}
