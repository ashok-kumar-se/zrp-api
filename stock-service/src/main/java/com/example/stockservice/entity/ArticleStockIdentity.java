package com.example.stockservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleStockIdentity implements Serializable {

    @NotBlank
    @Column(name = "article_id")
    @Size(min = 3, max = 20)
    private String articleId;

    @NotBlank
    @Column(name = "store_id")
    @Size(min = 3, max = 10)
    private String storeId;

    @NotBlank
    @Column(name = "store_loc")
    @Size(min = 3, max = 10)
    private String storeLoc;
}
