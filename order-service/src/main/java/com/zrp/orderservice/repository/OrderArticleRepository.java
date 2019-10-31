package com.zrp.orderservice.repository;

import com.zrp.orderservice.entity.OrderArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderArticleRepository extends JpaRepository<OrderArticle, Long> {
    List<OrderArticle> findByWebOrderId(String webOrderId);
}
