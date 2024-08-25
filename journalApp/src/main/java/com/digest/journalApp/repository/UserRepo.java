package com.digest.journalApp.repository;

import com.digest.journalApp.entity.JournalEntry;
import com.digest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo  extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);
    User deleteByUsername(String username);
}
