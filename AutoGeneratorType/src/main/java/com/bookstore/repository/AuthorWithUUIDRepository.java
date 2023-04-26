package com.bookstore.repository;

import com.bookstore.entity.AuthorGood;
import com.bookstore.entity.AuthorWithUUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorWithUUIDRepository extends JpaRepository<AuthorWithUUID, UUID> {
}