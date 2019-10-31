package com.example.articleservice.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleDto {

    private String articleId;

    private String articleName;

    private String articleDepartment;

    private String articleGroup;

    private String imageUrl;

}
