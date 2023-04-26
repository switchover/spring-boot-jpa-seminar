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
        //   - spring.jpa.properties.hibernate.generate_statistics=true 설정을 통해 Session Metrics 정보가 출력됨
        //   - logging.level.org.hibernate.type.descriptor.sql=TRACE 설정을 통해 바인딩된 파라미터 값 확인 가능
        //   - spring.jpa.properties.hibernate.jdbc.batch_size 30 확인
        // Step 1) 실행 후, 최종 Session Metrics 확인 (34 JDBC batches = 1,000 / 30 + 1)
        //         최종 sequence 값 10 확인 (10번 호출)
        return args -> {
            bookstoreService.batch1000Authors();
        };
    }
}
