package com.example.stockservice.service;

import com.example.stockservice.entity.ArticleStockIdentity;
import com.example.stockservice.repository.StockRepository;
import com.example.stockservice.service.impl.StockServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(StockServiceTest.class);

    @Mock
    StockRepository stockRepository;

    @InjectMocks
    StockServiceImpl stockServiceImpl;

    private static final String articleId = "152648";
    private static final String storeLoc = "0001";
    private static final String storeId = "1";

    @Test
    public void getStockByArticleIdAndStoreIdAndStoreLocTest() {
        logger.debug("Method: getStockByArticleIdAndStoreIdAndStoreLocTest(), Parameters: articleId - {}, storeId - {}, storeLoc - {}", articleId, storeId, storeLoc);
        stockServiceImpl.getStockByArticleIdAndStoreIdAndStoreLoc(articleId, storeId, storeLoc);
        verify(stockRepository, times(1)).findById(new ArticleStockIdentity(articleId, storeId, storeLoc));
    }

}