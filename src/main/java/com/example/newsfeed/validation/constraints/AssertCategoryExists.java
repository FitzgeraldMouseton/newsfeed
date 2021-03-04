package com.example.newsfeed.validation.constraints;

import com.example.newsfeed.validation.validators.CategoryValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategoryValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AssertCategoryExists {

    String message() default "Выберите категорию для новости";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
