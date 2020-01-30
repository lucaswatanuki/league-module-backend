package com.backend.rcs.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccessRequest {
    @JsonProperty("id")
    private String id;
    @JsonProperty("expiration_date")
    private LocalDate expirationDate;
    @JsonProperty("payment_date")
    private LocalDate paymentDate;
    @JsonProperty("status")
    private String status;
}
