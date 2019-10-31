package com.example.stockservice.dto;

import com.example.stockservice.entity.ArticleStock;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("articleId")
    private String articleStockIdentityArticleId;
    @JsonProperty("storeId")
    private String articleStockIdentityStoreId;
    @JsonProperty("storeLoc")
    private String articleStockIdentityStoreLoc;
    @JsonProperty("stockQty")
    private int stockQty;
}
