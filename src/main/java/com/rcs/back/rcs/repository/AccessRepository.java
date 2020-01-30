package com.rcs.back.rcs.repository;

import com.rcs.back.rcs.document.AccessDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRepository extends MongoRepository<AccessDocument, String> {
}
