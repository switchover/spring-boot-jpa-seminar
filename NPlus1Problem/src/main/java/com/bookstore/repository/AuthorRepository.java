package com.bookstore.repository;

import com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Override
    @Query("SELECT a FROM Author a LEFT JOIN FETCH a.books b")
    //@EntityGraph(attributePaths = {"books"})
    List<Author> findAll();
}
