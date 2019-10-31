package com.example.articleservice.controller;

import com.example.articleservice.dto.ArticleDto;
import com.example.articleservice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable(value = "articleId") String articleId) {
        return new ResponseEntity<ArticleDto>(articleService.getArticleDetails(articleId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<ArticleDto>> getArticle(@RequestBody List<String> articleIds) {
        return new ResponseEntity<List<ArticleDto>>(articleService.getArticlesDetails(articleIds), HttpStatus.OK);
    }

}
