package com.Marks03.LibraryManagementSystem.Controller;

import com.Marks03.LibraryManagementSystem.Entity.BorrowedBooks;
import com.Marks03.LibraryManagementSystem.Entity.User;
import com.Marks03.LibraryManagementSystem.Service.AdminService;
import com.Marks03.LibraryManagementSystem.Service.BorrowService;
import com.Marks03.LibraryManagementSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private BorrowService borrowService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping
    public ResponseEntity<User> createAdmin(@RequestBody User user) {
        adminService.saveAdmin(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/borrow-record")
    public ResponseEntity<List<BorrowedBooks>> borrowRecord() {
        List<BorrowedBooks> borrowedBooksList = borrowService.BorrowedBooksRecord();
        if(borrowedBooksList != null) {
            return new ResponseEntity<>(borrowedBooksList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
