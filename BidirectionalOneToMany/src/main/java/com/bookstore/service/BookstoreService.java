package com.bookstore.service;

import com.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.AuthorRepository;

@Service
public class BookstoreService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookstoreService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public void insertAuthorWithBooks() {
        Author author = new Author();
        author.setName("Alicia Tom");
        author.setAge(38);
        author.setGenre("Anthology");

        Book book = new Book();
        book.setIsbn("001-AT");
        book.setTitle("The book of swords");

        author.addBook(book); // use addBook() helper

        authorRepository.save(author);
    }

    @Transactional
    public void deleteBookOfAuthor() {
        Author author = authorRepository.fetchByName("Alicia Tom");
        Book book = author.getBooks().get(0);

        author.removeBook(book); // use removeBook() helper
    }

    public void insertOtherAuthorWithBooks() {
        Author author = new Author();
        author.setName("Joana Nimar");
        author.setAge(34);
        author.setGenre("History");

        Book book01 = new Book();
        book01.setIsbn("001-JN");
        book01.setTitle("A History of Ancient Prague");

        Book book02 = new Book();
        book02.setIsbn("002-JN");
        book02.setTitle("A People's History");

        Book book03 = new Book();
        book03.setIsbn("003-JN");
        book03.setTitle("World History");

        author.addBook(book01);
        author.addBook(book02);
        author.addBook(book03);

        authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    public void selectAuthorOnly() {
        Author author = authorRepository.findById(2L).orElseThrow();
        System.out.println(author);
    }

    @Transactional(readOnly = true)
    public void selectAuthorWithBooks() {
        Author author = authorRepository.findById(2L).orElseThrow();
        //Author author = authorRepository.fetchByName("Joana Nimar");

        author.getBooks().forEach(
            System.out::println
        );
    }

    @Transactional(readOnly = true)
    public void selectBook() {
        Book book = bookRepository.findById(2L).orElseThrow();

        System.out.println(book);
    }

    @Transactional
    public void deleteAllBooksOfAuthor() {
        Author author = authorRepository.fetchByName("Joana Nimar");
        author.removeBooks(); // use removeBooks() helper
    }
}
