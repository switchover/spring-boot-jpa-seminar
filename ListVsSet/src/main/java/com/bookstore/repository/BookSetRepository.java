package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookstore.entity.BookSet;

@Repository
public interface BookSetRepository extends JpaRepository<BookSet, Long> {
}
