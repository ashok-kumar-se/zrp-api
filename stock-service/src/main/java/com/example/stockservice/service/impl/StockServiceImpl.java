package com.example.stockservice.service.impl;

import com.example.stockservice.dto.ArticleStockDto;
import com.example.stockservice.entity.ArticleStockIdentity;
import com.example.stockservice.exception.CustomException;
import com.example.stockservice.entity.ArticleStock;
import com.example.stockservice.repository.StockRepository;
import com.example.stockservice.service.StockService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class StockServiceImpl implements StockService {

    private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    // Initialize stock repository
    @Autowired
    private StockRepository stockRepository;

    // Initialize model mapping
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Fetch stock details for a given article Id, store Id and store location
     *
     * @param articleId string
     * @param storeId string
     * @param storeLoc string
     * @return Object of type ArticleStockDto
     */
    @Override
    public ArticleStockDto getStockByArticleIdAndStoreIdAndStoreLoc(String articleId, String storeId, String storeLoc) {
        logger.debug("Method: getStockByArticleIdAndStoreIdAndStoreLoc(), Params: articleId - {}, storeId - {}, storeLoc - {}", articleId, storeId, storeLoc);
        Optional<ArticleStock> articleStock = stockRepository.findById(new ArticleStockIdentity(articleId, storeId, storeLoc));
        if (articleStock.isPresent()) {
            return modelMapper.map(articleStock.get(), ArticleStockDto.class);
        }
        throw new CustomException("Stock details for the combination [articleId :" + articleId + ", storeId :" + storeId + ", storeLoc : " + storeLoc + "] doesn't exist", HttpStatus.NOT_FOUND);
    }
}
