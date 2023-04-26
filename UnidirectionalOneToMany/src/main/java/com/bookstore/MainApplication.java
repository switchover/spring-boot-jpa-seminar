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
        // Step 0) 기존 테이블들 삭제 (각 단계 별 실행 필요)
        // Step 1) Author 엔터티에서 @OrderColumn 및 @JoinColumn 없이 실행 : 생성 테이블 확인 / 추가 DML 확인
        // Step 2) @OrderColumn 지정 후 실행 : 생성 테이블 확인 / 추가 DML 확인
        // Step 3) @OrderColumn 제외 및 @JoinColum 지정 후 실행 : 생성 테이블 확인 / 추가 DML 확인
        return args -> {
            System.out.println("\nInsert one author with three books  ...");
            bookstoreService.insertAuthorWithBooks();

            System.out.println("\n---------------------------------------------");

            System.out.println("\nInsert new book to an author  ...");
            bookstoreService.insertNewBook();

            System.out.println("\n---------------------------------------------");

            System.out.println("\nDelete last book of an author  ...");
            bookstoreService.deleteLastBook();

            System.out.println("\n---------------------------------------------");

            System.out.println("\nDelete first book of an author  ...");
            bookstoreService.deleteFirstBook();
        };
    }
}
