package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookstore.entity.BookList;

@Repository
public interface BookListRepository extends JpaRepository<BookList, Long> {
}
