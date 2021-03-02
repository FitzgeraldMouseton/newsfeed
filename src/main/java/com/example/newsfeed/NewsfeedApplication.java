package com.example.newsfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewsfeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsfeedApplication.class, args);
    }

}

/*
Разработать веб-приложение по управлению новостной лентой на сайте.

Разработать веб-приложение по управлению новостной лентой на сайте.
Каждая новость состоит из названия, содержания, даты публикации и категории, к которой относится новость.

Каждая категория содержит название, и к ней может быть привязано несколько новостей.

Приложение должно предоставлять следующие возможности по работе с новостями:
- просматривать список новостей
- поиск новости по категории (возможность выбрать из существующих категорий), названию и содержанию
- создание и редактирование новости
- удаление новости

Приложение должно быть написано на языке Java. Для реализации должны использоваться следующие технологии: spring, hibernate, spring mvc.
 */
