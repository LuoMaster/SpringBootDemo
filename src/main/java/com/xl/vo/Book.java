package com.xl.vo;

import lombok.Data;

@Data
public class Book {
    private String name;
    private String content;

    public Book(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public Book() {
    }
}
