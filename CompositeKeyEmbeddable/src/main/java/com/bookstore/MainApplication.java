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
        // Step 1) @Embeddable 및 @EmbeddedId 구성 확인
        // Step 2) 일반적인 처리 실행 후, 특별한 문제 없음 확인
        return args -> {
            System.out.println("\nAdd author with books ...");
            bookstoreService.addAuthorWithBooks();

            System.out.println("\nFetch author by name ...");
            bookstoreService.fetchAuthorByName();

            System.out.println("\nRemove a book of an author ...");
            bookstoreService.removeBookOfAuthor();

            System.out.println("\nRemove author (and books via cascade) ...");
            bookstoreService.removeAuthor();
        };
    }
}
