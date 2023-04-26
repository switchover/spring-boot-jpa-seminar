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
        // Step 1) Book 엔터티 상, @ManyToOne FetchType.LAZY 지정 삭제 후(EAGER) 실행 : 추가 SELECT 확인
        // Step 2) BookstoreService insertNewBook() 메서드에서 getOne() 대신 findBy*()로 변경 후 실행 : 불필요 author SELECT 확인
        // Step 3) BookstoreService insertNewBook() 메서드에서 save() 이후 변경 처리 포함 : UPDATE 확인
        // Step 4) Step 3에서 @Transactional 제거 후 다시 확인
        return args -> {
            System.out.println("\nInsert new book to an author ...");
            System.out.println("---------------------------------------------");
            bookstoreService.insertNewBook();
            System.out.println("---------------------------------------------");

            System.out.println("\nList all books of an author ...");
            System.out.println("---------------------------------------------");
            bookstoreService.fetchBooksOfAuthorById();
            System.out.println("---------------------------------------------");

            System.out.println("\nList a page books (first page with two books) of an author ...");
            System.out.println("---------------------------------------------");
            bookstoreService.fetchPageBooksOfAuthorById();
            System.out.println("---------------------------------------------");

            System.out.println("\nFetch a list of books and add new book ...");
            System.out.println("---------------------------------------------");
            bookstoreService.fetchBooksOfAuthorByIdAndAddNewBook();
            System.out.println("---------------------------------------------");

            System.out.println("\nFetch a list of books and delete the first book ...");
            System.out.println("---------------------------------------------");
            bookstoreService.fetchBooksOfAuthorByIdAndDeleteFirstBook();
            System.out.println("---------------------------------------------");
        };
    }
}
