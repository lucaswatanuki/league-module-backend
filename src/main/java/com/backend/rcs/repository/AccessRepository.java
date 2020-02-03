package com.backend.rcs.repository;

import com.backend.rcs.document.AccessDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface AccessRepository extends MongoRepository<AccessDocument, String> {
}
