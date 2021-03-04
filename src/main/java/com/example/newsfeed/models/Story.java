package com.example.newsfeed.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "news")
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    @Column(columnDefinition="TEXT")
    private String text;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @ManyToOne
    private Category category;
}
