package com.example.articleservice.service;


import  static org.mockito.Mockito.*;

import com.example.articleservice.exceptions.CustomException;
import com.example.articleservice.repository.ArticleRepository;
import com.example.articleservice.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceTest.class);

    @Mock
    ArticleRepository articleRepository;

    @InjectMocks
    ArticleServiceImpl articleServiceImpl;

    private static final String articleId = "152648";
    List<String> articleIds = Arrays.asList("152648","152645");

    @Test
    public void getArticleByArticleIdTest() throws CustomException {
        articleServiceImpl.getArticleByArticleId(articleId);
        verify(articleRepository, times(1)).findById(articleId);
    }

    @Test
    public void getArticlesByArticleIdsTest() {
        articleServiceImpl.getArticlesByArticleIds(articleIds);
        verify(articleRepository, times(1)).findByArticleIdIn(articleIds);
    }

}