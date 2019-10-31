package com.example.stockservice.service;

import com.example.stockservice.dto.ArticleStockDto;

public interface StockService {
    ArticleStockDto getArticleStock(String articleId, String storeId,String storeLoc);
}
