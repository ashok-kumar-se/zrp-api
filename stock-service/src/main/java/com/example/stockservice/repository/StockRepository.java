package com.example.stockservice.repository;

import com.example.stockservice.entity.ArticleStock;
import com.example.stockservice.entity.ArticleStockIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<ArticleStock, ArticleStockIdentity> {
    Optional<ArticleStock> findById(ArticleStockIdentity articleStockIdentity);
}
