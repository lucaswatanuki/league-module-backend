package com.backend.rcs.utils;

import com.backend.rcs.controller.request.AccessRequest;
import com.backend.rcs.controller.request.UserRequest;
import com.backend.rcs.controller.response.AccessResponse;
import com.backend.rcs.controller.response.UserResponse;
import com.backend.rcs.document.AccessDocument;
import com.backend.rcs.document.UserDocument;
import com.backend.rcs.repository.AccessRepository;
import com.backend.rcs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converter {

    private AccessRepository accessRepository;

    @Autowired
    public Converter(AccessRepository accessRepository) {
        this.accessRepository = accessRepository;
    }

    public static AccessResponse toAccessResponse(AccessDocument accessDocument) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        AccessResponse accessResponse = new AccessResponse();
        accessResponse.setId(accessDocument.getId());
        accessResponse.setExpirationDate(accessDocument.getExpirationDate().format(format));
        accessResponse.setPaymentDate(accessDocument.getPaymentDate().format(format));
        accessResponse.setStatus(accessDocument.getStatus());
        return accessResponse;
    }

    public static AccessDocument toAccessDocument(AccessRequest accessRequest) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // LocalDate data = LocalDate.parse(accessRequest.getExpirationDate(), format);
        AccessDocument accessDocument = new AccessDocument();
        accessDocument.setPaymentDate(LocalDate.parse(accessRequest.getPaymentDate(), format));
        return accessDocument;
    }

    public static UserResponse toUserResponse(UserDocument userDocument){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userDocument.getId());
        userResponse.setAccess(userDocument.getAccess());
        userResponse.setEmail(userDocument.getEmail());
        userResponse.setName(userDocument.getName());
        return userResponse;
    }


}
