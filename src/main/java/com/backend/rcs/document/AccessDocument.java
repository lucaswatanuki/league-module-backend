package com.backend.rcs.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
public class AccessDocument {
    @Id
    private String id;
    private LocalDate expirationDate;
    private LocalDate paymentDate;
    private String status;
}
