package com.backend.rcs.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessResponse {
    @JsonProperty("id")
    private String id;
    @JsonProperty("expiration_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String expirationDate;
    @JsonProperty("payment_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String paymentDate;
    @JsonProperty("status")
    private String status;
}
