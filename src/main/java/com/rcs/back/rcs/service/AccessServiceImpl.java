package com.rcs.back.rcs.service;

import com.rcs.back.rcs.controller.request.AccessRequest;
import com.rcs.back.rcs.controller.response.AccessResponse;
import com.rcs.back.rcs.controller.response.UserResponse;
import com.rcs.back.rcs.document.AccessDocument;
import com.rcs.back.rcs.document.AccessDocument;
import com.rcs.back.rcs.repository.AccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AccessServiceImpl implements AccessService{

    private AccessRepository accessRepository;

    @Autowired
    public AccessServiceImpl(AccessRepository accessRepository) {
        this.accessRepository = accessRepository;
    }

    @Override
    public AccessResponse save(AccessRequest accessRequest) {
        return toAccessResponse(accessRepository.save(toAccessDocument(accessRequest)));
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

    public AccessResponse toAccessResponse(AccessDocument accessDocument){
        AccessResponse accessResponse = new AccessResponse();
        accessResponse.setId(accessDocument.getId());
        accessResponse.setExpirationDate(accessDocument.getExpirationDate());
        accessResponse.setPaymentDate(accessDocument.getPaymentDate());
        accessResponse.setStatus(accessDocument.getStatus());
        return accessResponse;
    }

    public AccessDocument toAccessDocument(AccessRequest accessRequest){
        AccessDocument accessDocument = new AccessDocument();
        accessDocument.setId(accessRequest.getId());
        accessDocument.setExpirationDate(accessRequest.getExpirationDate());
        accessDocument.setPaymentDate(accessRequest.getPaymentDate());
        accessDocument.setStatus(accessRequest.getStatus());
        return accessDocument;
    }
}
