package com.apitest.models;

import lombok.Data;

@Data
public class Todo {
    private Integer id;
    private Integer userId;
    private String title;
    private Boolean completed;
} 