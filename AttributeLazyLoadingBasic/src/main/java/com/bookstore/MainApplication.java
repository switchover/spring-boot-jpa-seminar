package com.bookstore;

import com.bookstore.dto.AuthorDto;
import com.bookstore.entity.Author;
import com.bookstore.service.BookstoreService;
import java.util.List;
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
        // Step 1) 실행 후, 개별 메서드에 대해 다음을 확인
        //   - fetchAuthorsByAgeGreaterThanEqual() : avatar 컬럼 SELECT 미포함 확인
        //   - fetchAuthorAvatarViaId() : 2개 SELECT 호출 확인 (lazy loading 방식)
        //   - fetchAuthorsDetailsByAgeGreaterThanEqual() : N + 1 문제 (lazy loadiing으로 인한 문제)
        //   - fetchAuthorsWithAvatarsByAgeGreaterThanEqual() : DTO 및 JPQL을 통해 같이 가져오는 방식 확인
        // Step 2) 맨 아래 주석처리된 메서드 호출 시 오류 발생 확인
        return args -> {
            System.out.println("Persisting several authors ...");
            bookstoreService.createAuthors();

            System.out.println("\nFetch authors older than 40  ...");
            List<Author> authorsOld40 = bookstoreService.fetchAuthorsByAgeGreaterThanEqual(40);
            System.out.println(authorsOld40);

            System.out.println("\nFetch the avatar of author with id 1  ...");
            byte[] authorAvatar = bookstoreService.fetchAuthorAvatarViaId(1L);
            System.out.println(authorAvatar.length + " bytes");

            System.out.println("\nN+1 (avoid this)  ...");
            List<Author> authorsDetails = bookstoreService.fetchAuthorsDetailsByAgeGreaterThanEqual(40);
            System.out.println(authorsDetails);

            System.out.println("\nFetching DTO including avatars  ...");
            List<AuthorDto> authorsWithAvatars = bookstoreService.fetchAuthorsWithAvatarsByAgeGreaterThanEqual(40);
            authorsWithAvatars.forEach(a -> System.out.println(a.getName() + ", " + a.getAvatar()));

            // LazyInitializationException 발생
            // - spring.jpa.open-in-view=true 기본값으로 변경하면 오류가 발생하지 않으나,
            // - Hibernate Bytecode Enhancement를 사용하는 경우 spring.jpa.open-in-view=true 설정은 무효화됨
            //authorsOld40.forEach(a -> System.out.println(a.getName() + " has an avatar with " + a.getAvatar() + " bytes"));
        };
    }
}
