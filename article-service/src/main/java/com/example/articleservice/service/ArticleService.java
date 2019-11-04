package com.example.articleservice.service;

import com.example.articleservice.dto.ArticleDto;

import java.util.List;


public interface ArticleService {
    ArticleDto getArticleByArticleId(String articleId);

    List<ArticleDto> getArticlesByArticleIds(List<String> articleIds);
}
