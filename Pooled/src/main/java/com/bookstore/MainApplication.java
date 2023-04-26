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
        // Step 0) PostgreSQL Sequence 사용 확인
        // Step 1) 실행 후, save3Authors() 부분에 2개 "nexval" 확인
        //    - 현재 내부적으로 갖는 값이 hi(highest boundary) 값에 해당
        // Step 2) saveAuthorNative() 메서드에 의해 등록된 id 값 확인 (201)
        //    - saveAuthorNative() 메서드는 seq.를 직접 처리하는 외부 시스템에 대한 가상 호출 (충돌되지 않음 확인)
        return args -> {
            System.out.println("Save 3 authors ...");
            bookstoreService.save3Authors();

            System.out.println("\nSave 4th author w/ native ...");
            bookstoreService.saveAuthorNative();
        };
    }
}
