package com.example.newsfeed.controllers;

import com.example.newsfeed.models.Category;
import com.example.newsfeed.services.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoriesService categoriesService;

    @GetMapping
    public Iterable<Category> getCategories() {
        return categoriesService.getAllCategories();
    }
}
