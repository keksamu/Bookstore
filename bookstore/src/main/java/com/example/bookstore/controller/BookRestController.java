package com.example.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class BookRestController {

    private BookRepository bookRepository;

    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value="/api/books", method=RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {
        return (List<Book>) bookRepository.findAll();
    }

    @RequestMapping(value="/api/books/{id}", method=RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
        return bookRepository.findById(bookId);
    }

    @RequestMapping(value="/api/books", method=RequestMethod.POST)
    public @ResponseBody Book addBook(@RequestBody Book newBook) {
        return bookRepository.save(newBook);
}

    @RequestMapping(value="/api/books/{id}", method=RequestMethod.DELETE)
    public void deleteBook(@PathVariable("id") Long bookId) {
        bookRepository.deleteById(bookId);
}



    

}
