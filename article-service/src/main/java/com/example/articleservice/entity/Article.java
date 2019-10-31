package com.example.articleservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "articles")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdDate", "modifiedDate"}, allowGetters = true)
public class Article implements Serializable {

    @Id
    @Column(name = "article_id")
    private String articleId;

    @NotNull
    @Column(name = "article_name")
    private String articleName;

    @NotBlank
    @Column(name = "article_department")
    private String articleDepartment;

    @NotBlank
    @Column(name = "article_group")
    private String articleGroup;

    @NotBlank
    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate;

    @Column(name = "modified_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date modifiedDate;

}
