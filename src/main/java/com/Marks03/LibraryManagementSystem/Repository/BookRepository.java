package com.Marks03.LibraryManagementSystem.Repository;

import com.Marks03.LibraryManagementSystem.Entity.Book;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, ObjectId> {

    Book findByTitle(String title);

    Book findByIsbn(String isbn);
}
