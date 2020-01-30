package com.rcs.back.rcs.service;

import com.rcs.back.rcs.controller.request.AccessRequest;
import com.rcs.back.rcs.controller.response.AccessResponse;

import java.util.List;

public interface AccessService {
    AccessResponse save(AccessRequest accessRequest);
    AccessResponse findById(String id);
    List<AccessResponse> findAllAccess();
    AccessResponse update(AccessRequest accessRequest);
    void delete(String id);
}
