package com.zrp.orderservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderArticleDto {

    private String webOrderId;

    private String articleId;

    private ArticleDto article;

    private int orderQty;

    private int articleStockQty = 0;

}