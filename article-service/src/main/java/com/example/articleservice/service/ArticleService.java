package com.example.articleservice.service;

import com.example.articleservice.dto.ArticleDto;

import java.util.List;


public interface ArticleService {
    ArticleDto getArticleDetails(String articleId);

    List<ArticleDto> getArticlesDetails(List<String> articleIds);
}
