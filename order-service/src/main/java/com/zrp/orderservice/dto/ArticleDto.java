package com.zrp.orderservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ArticleDto {

    private String articleId;

    private String articleName;

    private String articleDepartment;

    private String articleGroup;

    private String imageUrl;

}
