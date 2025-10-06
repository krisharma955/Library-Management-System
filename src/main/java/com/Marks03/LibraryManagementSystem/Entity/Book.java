package com.Marks03.LibraryManagementSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bookDB")
public class Book {

    @Id
    private ObjectId id;

    private String title;

    private String author;

    private String isbn;

    private boolean isAvailable = true;

}
