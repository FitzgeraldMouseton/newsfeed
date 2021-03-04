package com.example.newsfeed.mappers;

import com.example.newsfeed.models.Category;
import com.example.newsfeed.models.Story;
import com.example.newsfeed.models.dto.StoryDto;
import com.example.newsfeed.services.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class StoryMapper {

    private final CategoriesService categoriesService;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Story dtoToStory(StoryDto dto) {
        Story story = new Story();
        story.setId(dto.getId());
        story.setTitle(dto.getTitle());
        story.setText(dto.getText());
        story.setCreatedTime(LocalDateTime.parse(dto.getCreatedTime(), formatter));
        Category category = categoriesService.findByName(dto.getCategory());
        story.setCategory(category);
        return story;
    }
    
    public StoryDto storyToDto(Story story) {
        StoryDto dto = new StoryDto();
        dto.setId(story.getId());
        dto.setTitle(story.getTitle());
        dto.setText(story.getText());
        dto.setCreatedTime(formatter.format(story.getCreatedTime()));
        dto.setCategory(story.getCategory().getName());
        return dto;
    }
}
