package com.example.bookstore;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.bookstore.model.AppUser;
import com.example.bookstore.model.AppUserRepository;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;
import com.example.bookstore.model.Category;
import com.example.bookstore.model.CategoryRepository;



@SpringBootApplication
public class BookstoreApplication {
	
		private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository categoryRepository, AppUserRepository urepository) {
	return (args) -> {
		log.info("save categories");
        Category Programming = categoryRepository.save(new Category("Programming"));
        Category Databases = categoryRepository.save(new Category("Databases"));
        Category Networking = categoryRepository.save(new Category("Networking"));

		log.info("save books");
	   	repository.save(new Book("Python Programming", "John Pork", 2020, "758-2394-24", 29.90, Programming));
        repository.save(new Book("SQL basics", "Jarvis Taylor", 2015, "4738-862", 50.90, Databases));
        repository.save(new Book("Coding made easy", "Jim D. Aston", 2007, "192-3867-759", 43.99, Networking));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        AppUser user1 = new AppUser("user", encoder.encode("userpassword"), "user@email.com", "USER");
        AppUser user2 = new AppUser("admin", encoder.encode("adminpassword"), "admin@email.com", "ADMIN");
        urepository.save(user1);
        urepository.save(user2);

		log.info("fetch all books");
			for (Book book : repository.findAll()) {
    		log.info(book.toString());
			}
		};
	}
}