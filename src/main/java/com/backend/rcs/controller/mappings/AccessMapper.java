package com.backend.rcs.controller.mappings;

import com.backend.rcs.controller.request.AccessRequest;
import com.backend.rcs.controller.response.AccessResponse;
import com.backend.rcs.document.AccessDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AccessMapper {
    AccessDocument toAccessDocument(AccessRequest accessRequest);

    AccessResponse toAccessResponse(AccessDocument accessDocument);
}
