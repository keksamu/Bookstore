package com.example.bookstore;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;
import com.example.bookstore.model.CategoryRepository;

@SpringBootTest(classes = BookstoreApplication.class)
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    public void findByAuthorShouldReturnBook() {
        List<Book> books = bookRepository.findByAuthor("John Pork");

        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Python Programming");

    }

    @Test
    public void deleteNewBook() {
        List<Book> books = bookRepository.findByAuthor("Jim D. Aston");
        Long bookId = books.get(0).getId();
        bookRepository.deleteById(bookId);
        List<Book> deletedBooks = bookRepository.findByAuthor("Jim D. Aston");
        assertThat(deletedBooks).hasSize(0);
    }

    @Test
    public void createNewBook() {
        Book book = new Book("Java Programming", "John Doe", 2021, "787-2323-24", 29.50, categoryRepository.findByName("Programming").get(0));
        bookRepository.save(book);
        assertThat(book.getId()).isNotNull();
    }



};
