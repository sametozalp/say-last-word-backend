package com.ozalp.auth_service.business.mappers;

import com.ozalp.auth_service.business.dtos.requests.AuthRegisterRequest;
import com.ozalp.auth_service.business.dtos.responses.AuthResponse;
import com.ozalp.auth_service.entities.Auth;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    Auth toEntity(AuthRegisterRequest request);

    AuthResponse toResponse(Auth auth);


}
