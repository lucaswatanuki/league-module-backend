package com.backend.rcs.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessRequest {
    @JsonProperty("id")
    private String id;
    @JsonProperty("payment_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String paymentDate;
}
