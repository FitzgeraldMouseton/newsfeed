package com.example.newsfeed.mappers;

import com.example.newsfeed.models.Category;
import com.example.newsfeed.models.Story;
import com.example.newsfeed.models.dto.StoryDto;
import com.example.newsfeed.services.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoryMapper {

    private final CategoriesService categoriesService;

    public Story dtoToStory(StoryDto dto) {
        Story story = new Story();
        story.setId(dto.getId());
        story.setTitle(dto.getTitle());
        story.setText(dto.getText());
        Category category = categoriesService.findByName(dto.getCategory());
        story.setCategory(category);
        return story;
    }
    
    public StoryDto storyToDto(Story story) {
        StoryDto dto = new StoryDto();
        dto.setId(story.getId());
        dto.setTitle(story.getTitle());
        dto.setText(story.getText());
        dto.setCategory(story.getCategory().getName());
        return dto;
    }
}
