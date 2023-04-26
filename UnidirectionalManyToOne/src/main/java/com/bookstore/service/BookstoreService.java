package com.bookstore.service;

import java.util.List;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.repository.BookRepository;

@Service
public class BookstoreService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookstoreService(AuthorRepository authorRepository,
                            BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void insertNewBook() {
        Author author = authorRepository.getReferenceById(4L);  // getOne() deprecated
        // or, less efficient since a SELECT is triggered
        //Author author = authorRepository.findById(4L).orElseThrow();
        // or
        //Author author = authorRepository.findByName("Joana Nimar");

        Book book = new Book();
        book.setIsbn("003-JN");
        book.setTitle("History Of Present");
        book.setAuthor(author);

        bookRepository.save(book);

        book.setIsbn("not available");  // Dirty Checking mechanism w/ @Transactional
    }

    public void fetchBooksOfAuthorById() {
        List<Book> books = bookRepository.fetchBooksOfAuthorById(4L);

        System.out.println(books);
    }

    /*
    @Transactional
    public void fetchBooksOfAuthorById() {
        List<Book> books = bookRepository.fetchBooksOfAuthorById(4L);

        books.get(0).setIsbn("not available");
    }
    */

    public void fetchPageBooksOfAuthorById() {
        Page<Book> books = bookRepository.fetchPageBooksOfAuthorById(4L,
            PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "title")));

        books.get().forEach(System.out::println);
    }

    @Transactional
    public void fetchBooksOfAuthorByIdAndAddNewBook() {
        List<Book> books = bookRepository.fetchBooksOfAuthorById(4L);

        Book book = new Book();
        book.setIsbn("004-JN");
        book.setTitle("History Facts");
        book.setAuthor(books.get(0).getAuthor());

        books.add(bookRepository.save(book));

        System.out.println(books);
    }

    @Transactional
    public void fetchBooksOfAuthorByIdAndDeleteFirstBook() {
        List<Book> books = bookRepository.fetchBooksOfAuthorById(4L);

        bookRepository.delete(books.remove(0));

        System.out.println(books);
    }
}

