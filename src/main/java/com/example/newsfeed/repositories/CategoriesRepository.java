package com.example.newsfeed.repositories;

import com.example.newsfeed.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepository extends CrudRepository<Category, Long> {

    Category findByName(String name);
}
