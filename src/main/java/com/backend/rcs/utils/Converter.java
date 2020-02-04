package com.backend.rcs.utils;

import com.backend.rcs.controller.request.AccessRequest;
import com.backend.rcs.controller.request.UserRequest;
import com.backend.rcs.controller.response.AccessResponse;
import com.backend.rcs.controller.response.UserResponse;
import com.backend.rcs.document.AccessDocument;
import com.backend.rcs.document.UserDocument;
import com.backend.rcs.repository.AccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class Converter {

    private final AccessRepository accessRepository;

    @Autowired
    public Converter(AccessRepository accessRepository) {
        this.accessRepository = accessRepository;
    }

    public AccessResponse toAccessResponse(AccessDocument accessDocument) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        AccessResponse accessResponse = new AccessResponse();
        accessResponse.setId(accessDocument.getId());
        accessResponse.setExpirationDate(accessDocument.getExpirationDate().format(format));
        accessResponse.setPaymentDate(accessDocument.getPaymentDate().format(format));
        accessResponse.setStatus(accessDocument.getStatus());
        return accessResponse;
    }

    public AccessDocument toAccessDocument(AccessRequest accessRequest) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // LocalDate data = LocalDate.parse(accessRequest.getExpirationDate(), format);
        AccessDocument accessDocument = new AccessDocument();
        accessDocument.setPaymentDate(LocalDate.parse(accessRequest.getPaymentDate(), format));
        return accessDocument;
    }

    public UserResponse toUserResponse(UserDocument userDocument){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userDocument.getId());
        userResponse.setAccess(userDocument.getAccess());
        userResponse.setEmail(userDocument.getEmail());
        userResponse.setName(userDocument.getName());
        return userResponse;
    }

    public UserDocument toUserDocument(UserRequest userRequest){
        UserDocument userDocument = new UserDocument();
        userDocument.setId(userRequest.getId());
        userDocument.setEmail(userRequest.getEmail());
        userDocument.setAccess(accessRepository.findById(userRequest.getAccess()).orElse(null));
        userDocument.setName(userRequest.getName());
        return userDocument;
    }


}
