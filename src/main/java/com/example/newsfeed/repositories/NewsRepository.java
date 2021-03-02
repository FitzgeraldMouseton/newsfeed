package com.example.newsfeed.repositories;

import com.example.newsfeed.models.Category;
import com.example.newsfeed.models.Story;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NewsRepository extends CrudRepository<Story, Long> {

    List<Story> findAllBy();

    List<Story> findAllByCategory(Category category);

    @Query("SELECT s FROM Story s WHERE s.text LIKE %:query% or s.title LIKE %:query%")
    List<Story> findAllByQuery(String query);

    void deleteById(Long id);
}
