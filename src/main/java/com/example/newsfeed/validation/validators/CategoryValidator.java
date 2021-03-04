package com.example.newsfeed.validation.validators;

import com.example.newsfeed.services.CategoriesService;
import com.example.newsfeed.validation.constraints.AssertCategoryExists;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class CategoryValidator implements ConstraintValidator<AssertCategoryExists, String> {

    private final CategoriesService categoriesService;

    @Override
    public boolean isValid(String categoryName, ConstraintValidatorContext constraintValidatorContext) {
        return categoriesService.existsByName(categoryName);
    }

    @Override
    public void initialize(AssertCategoryExists constraintAnnotation) {

    }
}
