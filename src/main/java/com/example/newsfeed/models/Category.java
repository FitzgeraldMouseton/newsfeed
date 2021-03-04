package com.example.newsfeed.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "выберите категорию")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Story> news = new ArrayList<>();
}
