package com.example.newsfeed.services;

import com.example.newsfeed.exceptions.PageNotFoundException;
import com.example.newsfeed.models.Category;
import com.example.newsfeed.models.Story;
import com.example.newsfeed.repositories.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public List<Story> findAllNews() {
        return newsRepository.findAllBy();
    }

    public List<Story> findNewsByCategory(Category category) {
        return newsRepository.findAllByCategory(category);
    }

    public List<Story> findNewsByQuery(String query) {
        return newsRepository.findAllByQuery(query);
    }

    public void addStory(Story story) {
        newsRepository.save(story);
    }

    public void editStory(Story story, long id) {
        Story storyToEdit = newsRepository.findById(id)
                .orElseThrow(() -> new PageNotFoundException("Не найдена новость с id " +id));
        storyToEdit.setTitle(story.getTitle());
        storyToEdit.setText(story.getText());
        storyToEdit.setCategory(story.getCategory());
        addStory(storyToEdit);
    }

    public void deleteStory(Long id) {
        if (storyNotExist(id)) {
            throw new PageNotFoundException("Не найдена новость с id " + id);
        }
        newsRepository.deleteById(id);
    }

    private boolean storyNotExist(Long id) {
        return !newsRepository.existsById(id);
    }
}
