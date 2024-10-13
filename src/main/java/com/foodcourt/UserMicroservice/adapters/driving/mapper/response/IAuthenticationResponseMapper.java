package com.foodcourt.UserMicroservice.adapters.driving.mapper.response;

import com.foodcourt.UserMicroservice.adapters.driving.dto.response.AuthenticationResponse;
import com.foodcourt.UserMicroservice.domain.model.Authentication;

public interface IAuthenticationResponseMapper {
    AuthenticationResponse toResponse(Authentication authentication);
}
