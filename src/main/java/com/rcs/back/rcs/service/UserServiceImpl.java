package com.rcs.back.rcs.service;

import com.rcs.back.rcs.controller.request.UserRequest;
import com.rcs.back.rcs.controller.response.UserResponse;
import com.rcs.back.rcs.document.UserDocument;
import com.rcs.back.rcs.repository.AccessRepository;
import com.rcs.back.rcs.repository.UserRepository;
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
        return toUserResponse(userRepository.save(toUserDocument(userRequest)));
    }

    @Override
    public UserResponse findById(String id) {
        return toUserResponse(Objects.requireNonNull(userRepository.findById(id).orElse(null)));
    }

    @Override
    public List<UserResponse> findAllUsers() {
        List<UserResponse> userResponseList = new ArrayList<>();
        userRepository.findAll().forEach(userDocument -> {
            userResponseList.add(toUserResponse(userDocument));
        });
        return userResponseList;
    }

    @Override
    public UserResponse update(UserRequest userRequest) {
        return toUserResponse(userRepository.save(toUserDocument(userRequest)));
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    public UserResponse toUserResponse(UserDocument userDocument){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userDocument.getId());
        userResponse.setAccess(userDocument.getAccess().getStatus());
        userResponse.setEmail(userDocument.getEmail());
        userResponse.setName(userDocument.getName());
        return userResponse;
    }

    public UserDocument toUserDocument(UserRequest userRequest){
        UserDocument userDocument = new UserDocument();
        userDocument.setId(userRequest.getId());
        userDocument.setEmail(userDocument.getEmail());
        userDocument.setAccess(accessRepository.findById(userRequest.getAccess()).orElse(null));
        userDocument.setName(userRequest.getName());
        return userDocument;
    }

}
