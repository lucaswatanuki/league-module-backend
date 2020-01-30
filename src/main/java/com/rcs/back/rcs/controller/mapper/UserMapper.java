package com.rcs.back.rcs.controller.mapper;

import com.rcs.back.rcs.controller.request.UserRequest;
import com.rcs.back.rcs.controller.response.UserResponse;
import com.rcs.back.rcs.document.UserDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(UserDocument userDocument);
    UserDocument toUserDocument(UserRequest userRequest);
}
