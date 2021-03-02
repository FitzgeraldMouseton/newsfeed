package com.example.newsfeed.controllers;

import com.example.newsfeed.models.Category;
import com.example.newsfeed.models.Story;
import com.example.newsfeed.services.CategoriesService;
import com.example.newsfeed.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/news")
public class NewsController {

    private final NewsService newsService;
    private final CategoriesService categoriesService;

    @GetMapping
    public List<Story> findAllNews() {
        return newsService.findAllNews();
    }

    @GetMapping("category")
    public List<Story> findNewsByCategory(@RequestParam(name = "category") String categoryName) {
        Category category = categoriesService.findByName(categoryName);
        return newsService.findNewsByCategory(category);
    }

    @GetMapping("search")
    public List<Story> findNewsByQuery(@RequestParam String query) {
        return newsService.findNewsByQuery(query);
    }

    @PostMapping("add")
    public void addStory(@Valid @RequestBody Story story) {
        newsService.addStory(story);
    }

    @PutMapping("edit/{id}")
    public void editStory(@Valid @RequestBody Story story, @PathVariable long id) {
        newsService.editStory(story, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        newsService.deleteStory(id);
    }
}
