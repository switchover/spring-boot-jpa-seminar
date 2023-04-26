package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookstore.entity.AuthorList;

@Repository
public interface AuthorListRepository extends JpaRepository<AuthorList, Long> {
}
