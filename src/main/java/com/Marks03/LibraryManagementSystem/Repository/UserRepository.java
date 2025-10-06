package com.Marks03.LibraryManagementSystem.Repository;

import com.Marks03.LibraryManagementSystem.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUsername(String username);

}
