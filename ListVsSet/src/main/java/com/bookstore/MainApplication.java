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
        // Step 1) 실행 후 List와 Set에 대한 차이 확인 : book 제거 시 추가 INSERT 발생 여부
        return args -> {
            System.out.println("===================================================");
            System.out.println("Populate database and remove a book (List case) ...");
            System.out.println("===================================================");
            bookstoreService.persistAuthorWithBooksAndRemoveOneBookList();

            System.out.println("\n");
            System.out.println("==================================================");
            System.out.println("Populate database and remove a book (Set case) ...");
            System.out.println("==================================================");
            bookstoreService.persistAuthorWithBooksAndRemoveOneBookSet();
        };
    }
}
