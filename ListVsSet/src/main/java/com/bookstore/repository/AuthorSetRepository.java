package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookstore.entity.AuthorSet;

@Repository
public interface AuthorSetRepository extends JpaRepository<AuthorSet, Long> {
}
