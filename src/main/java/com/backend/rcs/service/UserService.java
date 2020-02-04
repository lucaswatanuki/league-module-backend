package com.backend.rcs.service;

import com.backend.rcs.controller.request.UserRequest;
import com.backend.rcs.controller.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse save(UserRequest userRequest);
    UserResponse findById(String id);
    List<UserResponse> findUsersByAccess(String access);
    List<UserResponse> findAllUsers();
    UserResponse update(UserRequest userRequest);
    void delete(String id);
}
