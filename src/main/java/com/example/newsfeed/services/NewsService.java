package com.example.newsfeed.services;

import com.example.newsfeed.exceptions.PageNotFoundException;
import com.example.newsfeed.mappers.StoryMapper;
import com.example.newsfeed.models.Category;
import com.example.newsfeed.models.Story;
import com.example.newsfeed.models.dto.StoryDto;
import com.example.newsfeed.models.dto.ResponseDto;
import com.example.newsfeed.repositories.NewsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final StoryMapper storyMapper;

    public List<Story> findAllNews() {
        return newsRepository.findAllBy();
    }

    public Story getStoryById(long id) {
        return newsRepository.findById(id).orElseThrow(PageNotFoundException::new);
    }

    public List<Story> findNewsByCategory(Category category) {
        return newsRepository.findAllByCategory(category);
    }

    public List<Story> findNewsByQuery(String query) {
        return newsRepository.findAllByQuery(query);
    }

    public ResponseDto addStory(StoryDto dto) {
        Story story = storyMapper.dtoToStory(dto);
        newsRepository.save(story);
        return new ResponseDto(true);
    }

    public ResponseDto editStory(StoryDto dto, long id) {
        Story editedStory = newsRepository.findById(id)
                .orElseThrow(() -> new PageNotFoundException("Не найдена новость с id " +id));
        Story story = storyMapper.dtoToStory(dto);
        story.setId(id);
        newsRepository.save(story);
        return new ResponseDto(true);
    }

    public ResponseDto deleteStory(Long id) {
        if (storyNotExist(id)) {
            throw new PageNotFoundException("Не найдена новость с id " + id);
        }
        newsRepository.deleteById(id);
        return new ResponseDto(true);
    }

    private boolean storyNotExist(Long id) {
        return !newsRepository.existsById(id);
    }
}
