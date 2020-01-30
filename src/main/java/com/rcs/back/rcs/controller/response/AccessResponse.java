package com.rcs.back.rcs.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccessResponse {
    @JsonProperty("id")
    private String id;
    @JsonProperty("expiration_date")
    private LocalDate expirationDate;
    @JsonProperty("payment_date")
    private LocalDate paymentDate;
    @JsonProperty("status")
    private String status;
}
