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
        // Step 1) 실행 후, 아래 3개 메서드 결과 비교
        //   - distinct 사용 2개 방식은 쿼리 상 차이는 없음 (Execution Plan 차이 확인 필요)
        return args -> {
            bookstoreService.fetchWithDuplicates();
            bookstoreService.fetchWithoutHint();
            bookstoreService.fetchWithHint();
        };
    }
}
