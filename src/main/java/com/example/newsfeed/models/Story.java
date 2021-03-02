package com.example.newsfeed.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "news")
@EntityListeners(AuditingEntityListener.class)
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 5, message = "Заголовок короче 5 символов")
    private String title;

    @NotNull
    @Size(min = 15, message = "Текст новости короче 15 символов")
    private String text;

    @CreatedDate
    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @ManyToOne
    private Category category;
}
