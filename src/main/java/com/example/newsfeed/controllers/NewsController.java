package com.example.newsfeed.controllers;

import com.example.newsfeed.models.Category;
import com.example.newsfeed.models.Story;
import com.example.newsfeed.models.dto.ResponseDto;
import com.example.newsfeed.models.dto.StoryDto;
import com.example.newsfeed.services.CategoriesService;
import com.example.newsfeed.services.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;
    private final CategoriesService categoriesService;

    @GetMapping
    public List<Story> findAllNews() {
        return newsService.findAllNews();
    }

    @GetMapping("/{id}")
    public Story getStoryById(@PathVariable long id) {
        return newsService.getStoryById(id);
    }

    @GetMapping("category")
    public List<Story> findNewsByCategory(@RequestParam(name = "category") String categoryName) {
        Category category = categoriesService.findByName(categoryName);
        return newsService.findNewsByCategory(category);
    }

    @GetMapping("/search")
    public List<Story> findNewsByQuery(@RequestParam String query) {
        return newsService.findNewsByQuery(query);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto addStory(@Valid @RequestBody StoryDto story) {
        return newsService.addStory(story);
    }

    @PutMapping("/edit/{id}")
    public ResponseDto editStory(@Valid @RequestBody StoryDto story, @PathVariable long id) {
        return newsService.editStory(story, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable long id) {
        return newsService.deleteStory(id);
    }
}
