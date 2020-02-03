package com.backend.rcs.service;

import com.backend.rcs.controller.request.AccessRequest;
import com.backend.rcs.controller.response.AccessResponse;

import java.util.List;

public interface AccessService {
    AccessResponse save(AccessRequest accessRequest);
    AccessResponse findById(String id);
    List<AccessResponse> findAllAccess();
    AccessResponse update(AccessRequest accessRequest);
    void delete(String id);
}
