package com.example.articleservice.controller;

import  static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.example.articleservice.dto.ArticleDto;
import com.example.articleservice.service.ArticleService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ArticleControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(ArticleControllerTest.class);

    @InjectMocks
    ArticleController articleController;

    @Mock
    ArticleService articleService;

    @Mock
    ArticleDto articleDto;

    private static final String articleId = "152648";
    List<String> articleIds = Arrays.asList("152648","152645");

    @Test
    public void getArticleByArticleIdTest() {
        when(articleService.getArticleByArticleId(articleId)).thenReturn(articleDto);
        assertEquals(articleController.getArticleByArticleId(articleId).getBody(), articleDto);
    }

    @Test
    public void getArticlesByArticleIdsTest() {
        List<ArticleDto> articles = new ArrayList<>();
        articles.add(articleDto);
        when(articleService.getArticlesByArticleIds(articleIds)).thenReturn(articles);
        assertEquals(articleController.getArticlesByArticleIds(articleIds).getBody().size(), articles.size());
    }

}