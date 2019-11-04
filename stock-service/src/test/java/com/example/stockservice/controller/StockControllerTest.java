package com.example.stockservice.controller;

import com.example.stockservice.dto.ArticleStockDto;
import com.example.stockservice.service.StockService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    @InjectMocks
    StockController stockController;

    @Mock
    StockService stockService;

    @Mock
    ArticleStockDto articleStockDto;

    private static final String articleId = "152648";
    private static final String storeLoc = "0001";
    private static final String storeId = "1";

    @Test
    public void getStockByArticleIdAndStoreIdAndStoreLocTest() {
        logger.debug("Method: getStockByArticleIdAndStoreIdAndStoreLocTest(), Parameters: articleId - {}, storeId - {}, storeLoc - {}", articleId, storeId, storeLoc);
        when(stockService.getStockByArticleIdAndStoreIdAndStoreLoc(articleId,storeId,storeLoc)).thenReturn(articleStockDto);
        assertEquals(stockController.getStockByArticleIdAndStoreIdAndStoreLoc(articleId,storeId,storeLoc).getBody(), articleStockDto);
    }
}
