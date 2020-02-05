package com.backend.rcs.service;

import com.backend.rcs.controller.request.UserRequest;
import com.backend.rcs.controller.response.UserResponse;
import com.backend.rcs.repository.UserRepository;
import com.backend.rcs.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Converter converter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, Converter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    @Override
    public UserResponse save(UserRequest userRequest) {
        return converter.toUserResponse(userRepository.save(converter.toUserDocument(userRequest)));
    }

    @Override
    public UserResponse findById(String id) {
        return converter.toUserResponse(Objects.requireNonNull(userRepository.findById(id).orElse(null)));
    }

    @Override
    public List<UserResponse> findUsersByAccess(String accessId) {
        List<UserResponse> userResponseList = new ArrayList<>();
        userRepository.findAll().stream()
                .filter(user -> user.getAccess().getId().contains(accessId))
                .forEach(userDocument -> userResponseList.add(converter.toUserResponse(userDocument)));
        return userResponseList;
    }

    @Override
    public List<UserResponse> findAllUsers() {
        List<UserResponse> userResponseList = new ArrayList<>();
        userRepository.findAll().forEach(userDocument -> userResponseList.add(converter.toUserResponse(userDocument)));
        return userResponseList;
    }

    @Override
    public UserResponse update(UserRequest userRequest) {
        return converter.toUserResponse(userRepository.save(converter.toUserDocument(userRequest)));
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }
}
