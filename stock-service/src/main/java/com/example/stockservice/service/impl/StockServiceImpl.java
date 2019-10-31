package com.example.stockservice.service.impl;

import com.example.stockservice.dto.ArticleStockDto;
import com.example.stockservice.entity.ArticleStockIdentity;
import com.example.stockservice.exception.CustomException;
import com.example.stockservice.entity.ArticleStock;
import com.example.stockservice.repository.StockRepository;
import com.example.stockservice.service.StockService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Fetch stock details for a given article id
     *
     * @param articleId
     * @return
     */
    @Override
    public ArticleStockDto getArticleStock(String articleId, String storeId, String storeLoc) {
        Optional<ArticleStock> articleStock = stockRepository.findById(new ArticleStockIdentity(articleId, storeId, storeLoc));
        if (articleStock.isPresent()) {
            return modelMapper.map(articleStock.get(), ArticleStockDto.class);
        }
        throw new CustomException("Stock details for the combination [articleId :" + articleId + ", storeId :" + storeId + ", storeLoc : " + storeLoc + "] doesn't exist", HttpStatus.NOT_FOUND);
    }
}
