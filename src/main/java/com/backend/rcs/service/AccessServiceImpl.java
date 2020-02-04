package com.backend.rcs.service;

import com.backend.rcs.controller.request.AccessRequest;
import com.backend.rcs.controller.response.AccessResponse;
import com.backend.rcs.document.AccessDocument;
import com.backend.rcs.repository.AccessRepository;
import com.backend.rcs.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AccessServiceImpl implements AccessService {

    private final AccessRepository accessRepository;
    private final Converter converter;

    @Autowired
    public AccessServiceImpl(AccessRepository accessRepository, Converter converter) {
        this.accessRepository = accessRepository;
        this.converter = converter;
    }

    @Override
    public AccessResponse save(AccessRequest accessRequest) {
        AccessDocument accessDocument = converter.toAccessDocument(accessRequest);
        accessDocument.setExpirationDate(accessDocument.getPaymentDate().plusMonths(1L));
        accessDocument.setStatus("paid");
        return converter.toAccessResponse((accessRepository.save(accessDocument)));
    }

    @Override
    public AccessResponse findById(String id) {
        return converter.toAccessResponse(Objects.requireNonNull(accessRepository.findById(id).orElse(null)));
    }

    @Override
    public List<AccessResponse> findAllAccess() {
        List<AccessResponse> accessResponseList = new ArrayList<>();
        accessRepository.findAll().forEach(accessDocument -> accessResponseList.add(converter.toAccessResponse(accessDocument)));
        return accessResponseList;
    }

    @Override
    public AccessResponse update(AccessRequest accessRequest) {
        AccessDocument accessDocument = converter.toAccessDocument(accessRequest);
        accessDocument.setExpirationDate(accessDocument.getPaymentDate().plusMonths(1L));
        if (accessDocument.getExpirationDate().isBefore(LocalDate.now())){
            accessDocument.setStatus("expired");
        } else accessDocument.setStatus("paid");
        return converter.toAccessResponse(accessRepository.save(accessDocument));
    }

    @Override
    public void delete(String id) {
        accessRepository.deleteById(id);
    }
}
