package com.example.articleservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "articles")
public class Article implements Serializable {

    @Id
    @Size(max = 18)
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
}
