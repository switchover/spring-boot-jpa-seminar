package com.bookstore;

import com.bookstore.entity.Author;
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
        // Step 1) 실행 후, Avoid 부분과 Recommended 부분 비교 ("State" 부분)
        //   - 참고 용어 : "Hydrated" state(Entity 정보를 Object[] 형태로 Persistence Context에 보관된 상태)
        // Step 2) Recommended 부분에서 2개의 Transaction이 분리된 형태 확인
        // Step 3) Author 엔터티에 "@DynamicUpdate" 지정 시, UPDATE 문 변경 확인 (전체 -> 변경된 컬럼만)
        return args -> {
            System.out.println("Avoid:\n------\n");
            Author authorRW = bookstoreService.fetchAuthorReadWriteMode();
            authorRW.setAge(authorRW.getAge() + 1);
            bookstoreService.updateAuthor(authorRW);

            System.out.println("\n=============================\n");

            System.out.println("Recommended:\n-----------\n");
            Author authorRO = bookstoreService.fetchAuthorReadOnlyMode();
            authorRO.setAge(authorRO.getAge() + 1);
            bookstoreService.updateAuthor(authorRO);
        };
    }
}
