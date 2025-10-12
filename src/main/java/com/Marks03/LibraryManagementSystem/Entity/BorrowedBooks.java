package com.Marks03.LibraryManagementSystem.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "borrowRecord")
public class BorrowedBooks {

    @Id
    private ObjectId id;

    private ObjectId userId;
    private ObjectId bookId;
    private String isbn;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private boolean returned;

}