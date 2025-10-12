package com.Marks03.LibraryManagementSystem.Service;

import com.Marks03.LibraryManagementSystem.Entity.Book;
import com.Marks03.LibraryManagementSystem.Entity.BorrowedBooks;
import com.Marks03.LibraryManagementSystem.Entity.User;
import com.Marks03.LibraryManagementSystem.Repository.BookRepository;
import com.Marks03.LibraryManagementSystem.Repository.BorrowedBooksRepository;
import com.Marks03.LibraryManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReturnService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BorrowedBooksRepository borrowedBooksRepository;

    public ResponseEntity<String> returnBook(String isbn, String username) {
        Book book = bookRepository.findByIsbn(isbn);
        if(book == null) return new ResponseEntity<>("Book not found!", HttpStatus.NOT_FOUND);
        if(book.isAvailable()) return new ResponseEntity<>("System Error", HttpStatus.BAD_REQUEST);

        User user = userRepository.findByUsername(username);
        if(user == null) return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        user.getBorrowedBooks().remove(book.getId());
        userRepository.save(user);

        book.setAvailable(true);
        bookRepository.save(book);

        BorrowedBooks borrowedBooks = borrowedBooksRepository.findByIsbn(book.getIsbn());
        borrowedBooks.setReturnDate(LocalDateTime.now());
        borrowedBooks.setReturned(true);
        borrowedBooksRepository.save(borrowedBooks);

        return new ResponseEntity<>("Book successfully returned", HttpStatus.OK);
    }

}