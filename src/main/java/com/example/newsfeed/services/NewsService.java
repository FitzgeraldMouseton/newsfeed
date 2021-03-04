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

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final CategoriesService categoriesService;
    private final StoryMapper storyMapper;

    public List<StoryDto> findAllNews() {
        List<Story> news = newsRepository.findAllBy();
        return getStoryDtos(news);
    }

    public StoryDto getStoryById(long id) {
        Story story = newsRepository.findById(id).orElseThrow(PageNotFoundException::new);
        return storyMapper.storyToDto(story);
    }

    public List<StoryDto> findNewsByCategory(Category category) {
        List<Story> news = newsRepository.findAllByCategory(category);
        return getStoryDtos(news);
    }

    public List<StoryDto> findNewsByQuery(String query) {
        List<Story> news = newsRepository.findAllByQuery(query);
        return getStoryDtos(news);
    }

    public ResponseDto addStory(StoryDto dto) {
        Story story = storyMapper.dtoToStory(dto);
        newsRepository.save(story);
        return new ResponseDto(true);
    }

    public ResponseDto editStory(StoryDto dto, long id) {
        Story storyToEdit = newsRepository.findById(id)
                .orElseThrow(() -> new PageNotFoundException("Не найдена новость с id " +id));
        storyToEdit.setTitle(dto.getTitle());
        storyToEdit.setText(dto.getText());
        storyToEdit.setCategory(categoriesService.findByName(dto.getCategory()));
        newsRepository.save(storyToEdit);
        return new ResponseDto(true);
    }

    public ResponseDto deleteStory(Long id) {
        if (storyNotExist(id)) {
            throw new PageNotFoundException("Не найдена новость с id " + id);
        }
        newsRepository.deleteById(id);
        return new ResponseDto(true);
    }

    private List<StoryDto> getStoryDtos(final Iterable<Story> news) {
        List<StoryDto> storyDtos = new ArrayList<>();
        news.forEach(story -> {
            StoryDto dto = storyMapper.storyToDto(story);
            storyDtos.add(dto);
        });
        return storyDtos;
    }

    private boolean storyNotExist(Long id) {
        return !newsRepository.existsById(id);
    }
}
