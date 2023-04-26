package com.bookstore.service;

import com.bookstore.entity.AuthorBad;
import com.bookstore.entity.AuthorGood;
import com.bookstore.entity.AuthorWithUUID;
import com.bookstore.repository.AuthorBadRepository;
import com.bookstore.repository.AuthorWithUUIDRepository;
import org.springframework.stereotype.Service;
import com.bookstore.repository.AuthorGoodRepository;

@Service
public class BookstoreService {
    private final AuthorGoodRepository authorGoodRepository;
    private final AuthorBadRepository authorBadRepository;
    private final AuthorWithUUIDRepository authorWithUUIDRepository;

    public BookstoreService(AuthorGoodRepository authorGoodRepository, AuthorBadRepository authorBadRepository, AuthorWithUUIDRepository authorWithUUIDRepository) {
        this.authorGoodRepository = authorGoodRepository;
        this.authorBadRepository = authorBadRepository;
        this.authorWithUUIDRepository = authorWithUUIDRepository;
    }

    public void persistGoodAuthor() {
        AuthorGood good = new AuthorGood();
        good.setName("Joana Nimar");
        good.setAge(34);
        good.setGenre("History");

        authorGoodRepository.save(good);
    }

    public void persistBadAuthor() {
        AuthorBad bad = new AuthorBad();
        bad.setName("Alicia Tom");
        bad.setAge(38);
        bad.setGenre("Anthology");

        authorBadRepository.save(bad);
    }

    public void persistAuthorWithUUID() {
        AuthorWithUUID author = new AuthorWithUUID();
        author.setName("Vincent Han");

        authorWithUUIDRepository.save(author);
    }
}