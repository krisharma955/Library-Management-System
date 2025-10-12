package com.Marks03.LibraryManagementSystem.Repository;

import com.Marks03.LibraryManagementSystem.Entity.Book;
import com.Marks03.LibraryManagementSystem.Entity.BorrowedBooks;
import com.Marks03.LibraryManagementSystem.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BorrowedBooksRepository extends MongoRepository<BorrowedBooks, ObjectId> {

    BorrowedBooks findByIsbn(String isbn);

}
