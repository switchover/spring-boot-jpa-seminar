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
        // Step 0) spring.jpa.properties.hibernate.default_batch_fetch_size 속성 제외 확인
        // Step 1) 실행 시, 첫 번째 서비스 메서드 호출은 (N + 1)개 SELECT 발생 확인
        // Step 2) 두 번째 서비스 메서드 호출은 1개 SELECT(outer join) 발생 확인
        // Step 3) spring.jpa.properties.hibernate.default_batch_fetch_size 속성 지정 후, 첫 번째 메서드 호출 SELECT 확인
        // Step 4) BookRepository의 findAll() 메서드로 override를 통해 JOIN FETCH로 변경 가능 (1개 SELECT)
        return args -> {
            System.out.println("\nFetch all books and authors  ...");
            bookstoreService.fetchBooksAndAuthors();

            System.out.println("\nFetch all authors and books  ...");
            bookstoreService.fetchAuthorsAndBooks();
        };
    }
}
