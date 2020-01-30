package com.backend.rcs.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class UserDocument {
    @Id
    private String id;
    private String name;
    private String email;
    private AccessDocument access;
}
