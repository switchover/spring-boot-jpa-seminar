package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {
    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        // Step 1) 아래 Avoid 부분과 Recommended 부분 비교
        return args -> {
            System.out.println("Avoid:\n------\n");
            bookstoreService.addBookToAuthorWithFindById();

            System.out.println("\n=============================\n");

            System.out.println("Recommended:\n-----------\n");
            bookstoreService.addBookToAuthor();
        };
    }
}
