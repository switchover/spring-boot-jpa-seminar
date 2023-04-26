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
        // Step 1) persistBadAuthor() 호출에 대한 DB 확인 (hibernate_sequence 테이블)
        // Step 2) persistGoodAuthor() 호출 시 별도 DML 없음
        // Step 3) persistAuthorWithUUID() 호출 후 UUID 생성 확인
        //   -  @Column(columnDefinition = "BINARY(16)") 컬럼 정의가 없으면 VARCHAR(255)으로 뒤에 0으로 padding된 문자열로 생성
        return args -> {
            System.out.println("\nPersist bad author ...");
            bookstoreService.persistBadAuthor();

            System.out.println("\nPersist good author ...");
            bookstoreService.persistGoodAuthor();

            System.out.println("\nPersist author with UUID ...");
            bookstoreService.persistAuthorWithUUID();
        };
    }
}
