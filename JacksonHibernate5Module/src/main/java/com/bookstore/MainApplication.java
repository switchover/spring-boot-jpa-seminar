package com.bookstore;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        // Step 0) Web application으로 기동됨 (아래 Hibernate5Module @Bean 주석 확인)
        // Step 1) http://localhost:8080/fetchwithbooks 호출 시 문제 없음 확인 (JOIN FETCH 방식)
        // Step 2) http://localhost:8080/fetchwithoutbooks 호출 시 "failed to lazily initialize" 오류 확인 (로드)
        // Step 3) Hibernate5Module Bean 활성화 후 재기동 및 http://localhost:8080/fetchwithoutbooks 호출도 문제 없음 확인
        //   - Author 엔터티에 "@JsonInclude(Include.NON_EMPTY)"가 지정돼 있어, null인 books JSON 항목 나타나지 않음
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }
}
