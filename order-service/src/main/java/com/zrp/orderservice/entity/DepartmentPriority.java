package com.zrp.orderservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "article_department_priorities")
public class DepartmentPriority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String store_id;

    private String article_department;

    private int priority;

}
