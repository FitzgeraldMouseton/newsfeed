package com.example.newsfeed.models.dto;

import com.example.newsfeed.validation.constraints.AssertCategoryExists;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class StoryDto {

    private long id;

    @Size(min = 5, message = "Заголовок короче 5 символов")
    private String title;

    @Size(min = 15, message = "Текст новости короче 15 символов")
    private String text;

    private String createdTime;

    @AssertCategoryExists
    private String category;
}
