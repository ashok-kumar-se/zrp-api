package com.example.articleservice.repository;

import com.example.articleservice.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
    Optional<Article> findByArticleId(String articleId);

    List<Article> findByArticleIdIn(List<String> articleId);
}
