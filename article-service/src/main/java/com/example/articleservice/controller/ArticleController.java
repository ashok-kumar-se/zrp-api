package com.example.articleservice.controller;

import com.example.articleservice.dto.ArticleDto;
import com.example.articleservice.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleDto> getArticleByArticleId(@PathVariable(value = "articleId") String articleId) {
        logger.debug("Method: getArticleByArticleId(), QueryParams: articleId - {}", articleId);
        return new ResponseEntity<ArticleDto>(articleService.getArticleByArticleId(articleId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<ArticleDto>> getArticlesByArticleIds(@RequestBody List<String> articleIds) {
        logger.debug("Method: getArticlesByArticleIds(), BodyParams: articleIds - {}", articleIds.toString());
        return new ResponseEntity<List<ArticleDto>>(articleService.getArticlesByArticleIds(articleIds), HttpStatus.OK);
    }

}
