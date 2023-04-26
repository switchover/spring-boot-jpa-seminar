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

    @Transactional
    public void deleteBookOfAuthorWithoutHelper() {
        Author author = authorRepository.fetchByName("Joana Nimar");
        Book book = author.getBooks().get(0);   // 001-JN

        author.getBooks().remove(book);
        //bookRepository.delete(book);

        //author.removeBook(book); // use removeBook() helper
        // or
        //book.setAuthor(null);
        //author.getBooks().remove(book);
        // or use "orphanRemoval = true"
    }

    public void checkAfterDeleteBookOfAuthorWithoutHelper() {
        Author author = authorRepository.fetchByName("Joana Nimar");

        System.out.println("===> " + author.getBooks());
    }
}
