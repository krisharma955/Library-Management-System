package com.Marks03.LibraryManagementSystem.Service;

import com.Marks03.LibraryManagementSystem.Entity.Book;
import com.Marks03.LibraryManagementSystem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public Book findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public void removeBook(Book book) {
        bookRepository.delete(book);
    }
}
