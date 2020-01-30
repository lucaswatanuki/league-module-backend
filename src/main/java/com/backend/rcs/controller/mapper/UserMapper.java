package com.backend.rcs.controller.mapper;

import com.backend.rcs.document.UserDocument;
import com.backend.rcs.controller.request.UserRequest;
import com.backend.rcs.controller.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(UserDocument userDocument);
    UserDocument toUserDocument(UserRequest userRequest);
}
