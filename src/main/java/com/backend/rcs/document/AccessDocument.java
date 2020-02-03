package com.backend.rcs.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "access")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessDocument {
    @Id
    private String id;
    private LocalDate expirationDate;
    private LocalDate paymentDate;
    private String status;
}
