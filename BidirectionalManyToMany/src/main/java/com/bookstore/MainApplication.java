package com.bookstore;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.bookstore.service.BookstoreService;

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
        // Step 0) 기존 테이블들 삭제 (각 단계 별 실행 필요)
        // Step 1) Book 엔터티 mappedBy 삭제 후 실헹 : 생성 테이블 확인
        return args -> {
            bookstoreService.insertAuthorsWithBooks();
        };
    }
}
