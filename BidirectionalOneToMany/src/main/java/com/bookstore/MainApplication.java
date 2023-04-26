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
        // Step 1) 기본 로딩 전략(OneToMany : lazy, ManyToOne : eager) 확인 및 ManyToOne eager -> lazy 변경 확인
        //   - 여기서는 Book 조회 후 Author 조회가 없어 lazy <-> eager 차이가 없음
        //   - (lazy 단점) BookstoreService selectAuthorWithBooks() 메서드 : 2개 SELECT가 실행됨을 확인
        //  Step 2) BookstoreService selectAuthorWithBooks() 메서드에서 findById() -> fetchByName() 변경 후 실행 : 1개 join SELECT 실행 확인
        return args -> {
            System.out.println("\nInsert author with books ...");
            bookstoreService.insertAuthorWithBooks();

            System.out.println("\nDelete a book of an author ...");
            bookstoreService.deleteBookOfAuthor();

            System.out.println("\nInsert other author with books ...");
            bookstoreService.insertOtherAuthorWithBooks();

            System.out.println("\nSelect an author only ...");
            bookstoreService.selectAuthorOnly();

            System.out.println("\nSelect an author with books ...");
            bookstoreService.selectAuthorWithBooks();

            System.out.println("\nSelect a book ...");
            bookstoreService.selectBook();

            System.out.println("\nDelete all book of an author ...");
            bookstoreService.deleteAllBooksOfAuthor();
        };
    }
}
