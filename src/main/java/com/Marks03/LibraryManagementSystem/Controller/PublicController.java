package com.Marks03.LibraryManagementSystem.Controller;

import com.Marks03.LibraryManagementSystem.Entity.Book;
import com.Marks03.LibraryManagementSystem.Entity.User;
import com.Marks03.LibraryManagementSystem.Service.BookService;
import com.Marks03.LibraryManagementSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @PostMapping("/add-user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.saveNewUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/all-books")
    public List<Book> getListOfBooks() {
        return bookService.getAll();
    }

}
