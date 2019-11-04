package com.example.articleservice.service.impl;

import com.example.articleservice.dto.ArticleDto;
import com.example.articleservice.entity.Article;
import com.example.articleservice.exceptions.CustomException;
import com.example.articleservice.repository.ArticleRepository;
import com.example.articleservice.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    // Initialize article repository
    @Autowired
    private ArticleRepository articleRepository;

    // Initialize model mapping
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Fetch the article details for a given article Id
     *
     * @param articleId - string
     * @return Object of type ArticleDto
     */
    @Override
    public ArticleDto getArticleByArticleId(String articleId) {
        Optional<Article> article = articleRepository.findByArticleId(articleId);
        if (article.isPresent()) {
            return modelMapper.map(article.get(), ArticleDto.class);
        }
        throw new CustomException("Article [" + articleId + "] details doesn't exist", HttpStatus.NOT_FOUND);
    }

    /**
     * Fetch list of article details for the given list of article Ids
     *
     * @param articleIds - list of string
     * @return List of type ArticleDto
     */
    @Override
    public List<ArticleDto> getArticlesByArticleIds(List<String> articleIds) {
        List<Article> articles = articleRepository.findByArticleIdIn(articleIds);
        if (!articles.isEmpty()) {
            return articles.stream()
                    .map(article -> modelMapper.map(article, ArticleDto.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
