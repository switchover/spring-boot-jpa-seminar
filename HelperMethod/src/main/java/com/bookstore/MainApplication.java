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
        // Step 1) Author 엔터티 현재 상태(no orphanRemoval) 확인
        // Step 2) BookstoreService deleteBookOfAuthorWithoutHelper() 메서드에서,
        //         "author.getBooks().remove(book);" 라인 만으로 삭제가 되지 않음
        // Step 3) "bookRepository.delete(book);" 라인 만으로도 삭제 되지 않음
        //   - 단, 2개 모두 처리하면 삭제됨
        // Step 4) 해당 메서드 아래에 제시된 해결 방법 중 하나로 실행 및 삭제 확인
        return args -> {
            System.out.println("\nInsert an author with books  ...");
            bookstoreService.insertOtherAuthorWithBooks();

            System.out.println("\nDelete a book of an author (w/o helper)...");
            bookstoreService.deleteBookOfAuthorWithoutHelper();

            System.out.println("\nCheck book list...");
            bookstoreService.checkAfterDeleteBookOfAuthorWithoutHelper();
        };
    }
}
