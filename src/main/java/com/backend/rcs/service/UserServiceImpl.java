package com.backend.rcs.service;

import com.backend.rcs.controller.request.UserRequest;
import com.backend.rcs.controller.response.UserResponse;
import com.backend.rcs.document.UserDocument;
import com.backend.rcs.repository.AccessRepository;
import com.backend.rcs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccessRepository accessRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AccessRepository accessRepository) {
        this.userRepository = userRepository;
        this.accessRepository = accessRepository;
    }

    @Override
    public UserResponse save(UserRequest userRequest) {
        UserDocument userDocument = userRepository.save(toUserDocument(userRequest));
        userDocument.setAccess(accessRepository.findById(userRequest.getAccess()).orElse(null));
        return toUserResponse(userDocument);
    }

    @Override
    public UserResponse findById(String id) {
        return toUserResponse(userRepository.findById(id).orElse(null));
    }

    @Override
    public List<UserResponse> findUsersByAccess(String accessId) {
        List<UserResponse> userResponseList = new ArrayList<>();
        userRepository.findAll().stream()
                .filter(user -> user.getAccess().getId().contains(accessId))
                .forEach(userDocument -> userResponseList.add(toUserResponse(userDocument)));
        return userResponseList;
    }

    @Override
    public List<UserResponse> findAllUsers() {
        List<UserResponse> userResponseList = new ArrayList<>();
        userRepository.findAll().forEach(userDocument -> userResponseList.add(toUserResponse(userDocument)));
        return userResponseList;
    }

    @Override
    public UserResponse update(UserRequest userRequest) {
        UserDocument userDocument = userRepository.save(toUserDocument(userRequest));
        userDocument.setAccess(accessRepository.findById(userRequest.getAccess()).orElse(null));
        return toUserResponse(userDocument);
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    public UserDocument toUserDocument(UserRequest userRequest){
        UserDocument userDocument = new UserDocument();
        userDocument.setAccess(accessRepository.findById(userRequest.getAccess()).orElse(null));
        userDocument.setEmail(userRequest.getEmail());
        userDocument.setName(userRequest.getName());
        return userDocument;
    }

    public UserResponse toUserResponse(UserDocument userDocument){
        UserResponse userResponse = new UserResponse();
        userResponse.setAccess(userDocument.getAccess());
        userResponse.setEmail(userDocument.getEmail());
        userResponse.setName(userDocument.getName());
        userResponse.setId(userDocument.getId());
        return userResponse;
    }
}
