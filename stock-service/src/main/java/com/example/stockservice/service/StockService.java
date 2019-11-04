package com.example.stockservice.service;

import com.example.stockservice.dto.ArticleStockDto;

public interface StockService {
    ArticleStockDto getStockByArticleIdAndStoreIdAndStoreLoc(String articleId, String storeId,String storeLoc);
}
