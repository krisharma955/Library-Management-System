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
import java.util.List;

@Component
public class BorrowService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowedBooksRepository borrowedBooksRepository;

    public ResponseEntity<String> borrowBook(String isbn, String username) throws Exception {
        Book book = bookRepository.findByIsbn(isbn);
        if(book == null) return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        if(!book.isAvailable()) return new ResponseEntity<>("Book already borrowed", HttpStatus.BAD_REQUEST);

        User user = userRepository.findByUsername(username);
        if(user == null) return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        user.getBorrowedBooks().add(book.getId());
        userRepository.save(user);

        book.setAvailable(false);
        bookRepository.save(book);

        BorrowedBooks record = BorrowedBooks.builder()
                .userId(user.getId())
                .bookId(book.getId())
                .isbn(book.getIsbn())
                .borrowDate(LocalDateTime.now())
                .returned(false)
                .build();
        borrowedBooksRepository.save(record);


        return new ResponseEntity<>("Book borrowed successfully", HttpStatus.OK);
    }

    public List<BorrowedBooks> BorrowedBooksRecord() {
        return borrowedBooksRepository.findAll();
    }

}
