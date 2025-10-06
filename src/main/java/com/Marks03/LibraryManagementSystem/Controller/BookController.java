package com.Marks03.LibraryManagementSystem.Controller;

import com.Marks03.LibraryManagementSystem.Entity.Book;
import com.Marks03.LibraryManagementSystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        bookService.saveBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @DeleteMapping("/isbn/{isbn}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable String isbn) {
        Book book = bookService.findBookByIsbn(isbn);
        if(book != null) {
            bookService.removeBook(book);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/isbn/{isbn}")
    public ResponseEntity<Book> updateBook(@RequestBody Book newBook, @PathVariable String isbn) {
        Book book = bookService.findBookByIsbn(isbn);
        if(book != null) {
            book.setTitle(!newBook.getTitle().isEmpty() ? newBook.getTitle() : book.getTitle());
            book.setAuthor(!newBook.getAuthor().isEmpty() ? newBook.getAuthor() : book.getAuthor());
            book.setIsbn(!newBook.getIsbn().isEmpty() ? newBook.getIsbn() : book.getIsbn());
            bookService.saveBook(book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
