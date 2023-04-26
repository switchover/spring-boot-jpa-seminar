package com.bookstore.repository;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    /*
    @Override
    @Query("SELECT b FROM Book b JOIN FETCH b.author a")
        //@Query("SELECT b, b.author FROM Book b")   // INNER JOIN
    List<Book> findAll();
    // */
}
