package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {
    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void batch1000Authors() {
        System.out.println("-----------------------\nStart of service method\n-----------------------");
        List<Author> authors = new ArrayList<>();

        for (int i = 1; i <= 1_000; i++) {
            Author author = new Author();
            author.setName("Author_" + i);

            authors.add(author);
        }

        authorRepository.saveAll(authors);
        System.out.println("---------------------\nEnd of service method\n---------------------");

        System.out.println("Last value of sequence : " + authorRepository.getCurrentSeqValue());
    }
}
