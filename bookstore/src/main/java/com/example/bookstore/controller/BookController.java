package com.example.bookstore.controller;


import com.example.bookstore.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/index")
    public Book getBook() {
        return new Book("Clean Code", "Robert C. Martin", 2008, "978-0132350884", 29.99);
    }
}