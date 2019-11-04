package com.example.articleservice.service;


import  static org.mockito.Mockito.*;

import com.example.articleservice.repository.ArticleRepository;
import com.example.articleservice.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;


@ExtendWith(SpringExtension.class)
public class ArticleServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    @Mock
    ArticleRepository articleRepository;

    @InjectMocks
    ArticleServiceImpl articleServiceImpl;

    private static final String articleId = "152648";
    List<String> articleIds = Arrays.asList("152648","152645");

    @Test
    public void getArticleByArticleIdTest() {
        articleServiceImpl.getArticleByArticleId(articleId);
        verify(articleRepository, times(1)).findByArticleId(articleId);
    }

    @Test
    public void getArticlesByArticleIdsTest() {
        articleServiceImpl.getArticlesByArticleIds(articleIds);
        verify(articleRepository, times(1)).findByArticleIdIn(articleIds);
    }

}