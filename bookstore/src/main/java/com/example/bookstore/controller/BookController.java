package com.example.bookstore.controller;


import com.example.bookstore.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.bookstore.model.BookRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }   

    @GetMapping("/index")
    public Book getBook() {
        return new Book("Clean Code", "Robert C. Martin", 2008, "978-0132350884", 29.99);
    }

    @RequestMapping(value="/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
        }


}