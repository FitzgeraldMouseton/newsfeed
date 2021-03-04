package com.example.newsfeed.services;

import com.example.newsfeed.models.Category;
import com.example.newsfeed.repositories.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    public Category findByName(String name) {
        return categoriesRepository.findByName(name);
    }

    public boolean existsByName(String name) {
        return categoriesRepository.existsByName(name);
    }

    public Iterable<Category> getAllCategories() {
        return categoriesRepository.findAll();
    }
}
