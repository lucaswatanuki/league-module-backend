package com.rcs.back.rcs.service;

import com.rcs.back.rcs.controller.request.UserRequest;
import com.rcs.back.rcs.controller.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    UserResponse save(UserRequest userRequest);
    UserResponse findById(String id);
    List<UserResponse> findAllUsers();
    UserResponse update(UserRequest userRequest);
    void delete(String id);
}
