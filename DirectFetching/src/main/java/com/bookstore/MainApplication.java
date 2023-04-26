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
        // Step 1) BookstoreService directFetching() 메서드에서 Direct fetch 3가지 방법 확인
        // Step 2) 실행 시, 동일 서비스 메서드(transaction 내 호출)안에서 엔터티 공유됨을 확인 (session-level repeatable reads)
        // Step 3) directFetching() 메서드에서 @Transactional 제거 후 확인
        // Step 4) log level 설정(application.properties) 지정 후 세부 처리 확인 가능
        // Step 5) BookstoreService fetchingViaReference() 메서드에서 findById()와 getReferenceById()과의 차이 확인
        return args -> {
            bookstoreService.directFetching();

            System.out.println("\nSame fetching once again...");
            bookstoreService.directFetching();

            System.out.println("\nOther test...");
            bookstoreService.fetchingViaReference();
        };
    }
}
