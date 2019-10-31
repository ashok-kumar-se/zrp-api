package com.example.stockservice.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "article_stock")
public class ArticleStock {

    @EmbeddedId
    private ArticleStockIdentity articleStockIdentity;

    @NotBlank
    @Column(name = "stock_qty")
    private int stockQty;
}