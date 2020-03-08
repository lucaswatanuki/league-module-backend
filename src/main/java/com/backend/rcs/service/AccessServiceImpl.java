package com.backend.rcs.service;

import com.backend.rcs.controller.mappings.AccessMapper;
import com.backend.rcs.controller.request.AccessRequest;
import com.backend.rcs.controller.response.AccessResponse;
import com.backend.rcs.document.AccessDocument;
import com.backend.rcs.repository.AccessRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AccessServiceImpl implements AccessService {

    private final AccessRepository accessRepository;

    @Autowired
    public AccessServiceImpl(AccessRepository accessRepository) {
        this.accessRepository = accessRepository;
    }

    @Override
    public AccessResponse save(AccessRequest accessRequest) {
        AccessDocument accessDocument = Mappers.getMapper(AccessMapper.class).toAccessDocument(accessRequest);
        accessDocument.setExpirationDate(accessDocument.getPaymentDate().plusMonths(1L));
        accessDocument.setStatus("paid");
        return Mappers.getMapper(AccessMapper.class).toAccessResponse(accessRepository.save(accessDocument));
    }

    @Override
    public AccessResponse findById(String id) {
        return Mappers.getMapper(AccessMapper.class).toAccessResponse(Objects.requireNonNull(accessRepository.findById(id).orElse(null)));
    }

    @Override
    public List<AccessResponse> findByStatus(String status) {
        List<AccessResponse> accessResponseList = new ArrayList<>();
        accessRepository.findAll().stream()
                .filter(accessDocument -> accessDocument.getStatus().contains(status))
                .forEach(accessDocument -> accessResponseList.add(Mappers.getMapper(AccessMapper.class).toAccessResponse(accessDocument)));
        return accessResponseList;
    }

    @Override
    public List<AccessResponse> findAllAccess() {
        List<AccessResponse> accessResponseList = new ArrayList<>();
        accessRepository.findAll().forEach(accessDocument -> accessResponseList.add(Mappers.getMapper(AccessMapper.class).toAccessResponse(accessDocument)));
        return accessResponseList;
    }

    @Override
    public AccessResponse update(AccessRequest accessRequest) {
        AccessDocument accessDocument = Mappers.getMapper(AccessMapper.class).toAccessDocument(accessRequest);
        accessDocument.setExpirationDate(accessDocument.getPaymentDate().plusMonths(1L));
        if (accessDocument.getExpirationDate().isBefore(LocalDate.now())){
            accessDocument.setStatus("expired");
        } else accessDocument.setStatus("paid");
        return Mappers.getMapper(AccessMapper.class).toAccessResponse(accessRepository.save(accessDocument));
    }

    @Override
    public void delete(String id) {
        accessRepository.deleteById(id);
    }
}
