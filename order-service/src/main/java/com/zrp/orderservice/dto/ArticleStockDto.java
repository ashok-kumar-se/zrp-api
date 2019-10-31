package com.zrp.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ArticleStockDto {
    private String articleId;
    private String storeId;
    private String StoreLoc;
    private int stockQty;
}
