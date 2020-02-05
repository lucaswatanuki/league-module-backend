package com.backend.rcs.utils;

import com.backend.rcs.controller.response.AccessResponse;
import com.backend.rcs.document.AccessDocument;
import com.backend.rcs.repository.AccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@EnableScheduling
public class PaymentValidator {

    private final AccessRepository accessRepository;

    private final long SECOND = 1000;
    private final long MINUTE = SECOND * 60;
    private final long HOUR = MINUTE * 60;

    @Autowired
    public PaymentValidator(AccessRepository accessRepository) {
        this.accessRepository = accessRepository;
    }

    @Scheduled(fixedDelay = HOUR)
    public void verifyPaymentPerMin() {
        accessRepository.findAll().stream()
                .filter(accessDocument -> accessDocument.getStatus().contains("paid"))
                .filter(accessDocument -> accessDocument.getExpirationDate().isBefore(LocalDate.now()))
                .forEach(access -> {
                    access.setId(access.getId());
                    access.setStatus("expired");
                    accessRepository.save(access);
                });
    }
}
