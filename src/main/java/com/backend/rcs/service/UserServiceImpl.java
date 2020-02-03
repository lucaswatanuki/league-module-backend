package com.backend.rcs.service;

import com.backend.rcs.document.UserDocument;
import com.backend.rcs.repository.AccessRepository;
import com.backend.rcs.repository.UserRepository;
import com.backend.rcs.controller.request.UserRequest;
import com.backend.rcs.controller.response.UserResponse;
import com.backend.rcs.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private AccessRepository accessRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AccessRepository accessRepository) {
        this.userRepository = userRepository;
        this.accessRepository = accessRepository;
    }

    @Override
    public UserResponse save(UserRequest userRequest) {
        return Converter.toUserResponse(userRepository.save(Converter.toUserDocument(userRequest)));
    }

    @Override
    public UserResponse findById(String id) {
        return Converter.toUserResponse(Objects.requireNonNull(userRepository.findById(id).orElse(null)));
    }

    @Override
    public List<UserResponse> findAllUsers() {
        List<UserResponse> userResponseList = new ArrayList<>();
        userRepository.findAll().forEach(userDocument -> {
            userResponseList.add(Converter.toUserResponse(userDocument));
        });
        return userResponseList;
    }

    @Override
    public UserResponse update(UserRequest userRequest) {
        return Converter.toUserResponse(userRepository.save(Converter.toUserDocument(userRequest)));
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

}
