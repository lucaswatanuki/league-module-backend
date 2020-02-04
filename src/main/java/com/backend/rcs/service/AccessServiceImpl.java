package com.backend.rcs.service;

import com.backend.rcs.controller.response.AccessResponse;
import com.backend.rcs.document.AccessDocument;
import com.backend.rcs.repository.AccessRepository;
import com.backend.rcs.controller.request.AccessRequest;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AccessServiceImpl implements AccessService {

    private AccessRepository accessRepository;

    @Autowired
    public AccessServiceImpl(AccessRepository accessRepository) {
        this.accessRepository = accessRepository;
    }

    @Override
    public AccessResponse save(AccessRequest accessRequest) {
        AccessDocument accessDocument = toAccessDocument(accessRequest);
        accessDocument.setExpirationDate(accessDocument.getPaymentDate().plusMonths(1L));
        accessDocument.setStatus("paid");
        return toAccessResponse((accessRepository.save(accessDocument)));
    }

    @Override
    public AccessResponse findById(String id) {
        return toAccessResponse(Objects.requireNonNull(accessRepository.findById(id).orElse(null)));
    }

    @Override
    public List<AccessResponse> findAllAccess() {
        List<AccessResponse> accessResponseList = new ArrayList<>();
        accessRepository.findAll().forEach(accessDocument -> {
            accessResponseList.add(toAccessResponse(accessDocument));
        });
        return accessResponseList;
    }

    @Override
    public AccessResponse update(AccessRequest accessRequest) {
        return toAccessResponse(accessRepository.save(toAccessDocument(accessRequest)));
    }

    @Override
    public void delete(String id) {
        accessRepository.deleteById(id);
    }


    public static AccessResponse toAccessResponse(AccessDocument accessDocument) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        AccessResponse accessResponse = new AccessResponse();
        accessResponse.setId(accessDocument.getId());
        accessResponse.setExpirationDate(accessDocument.getExpirationDate().format(format));
        accessResponse.setPaymentDate(accessDocument.getPaymentDate().format(format));
        accessResponse.setStatus(accessDocument.getStatus());
        return accessResponse;
    }

    public static AccessDocument toAccessDocument(AccessRequest accessRequest) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // LocalDate data = LocalDate.parse(accessRequest.getExpirationDate(), format);
        AccessDocument accessDocument = new AccessDocument();
        accessDocument.setPaymentDate(LocalDate.parse(accessRequest.getPaymentDate(), format));
        return accessDocument;
    }
}
