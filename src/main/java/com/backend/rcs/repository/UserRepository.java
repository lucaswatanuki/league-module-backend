package com.backend.rcs.repository;

import com.backend.rcs.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends MongoRepository<UserDocument, String> {
}
