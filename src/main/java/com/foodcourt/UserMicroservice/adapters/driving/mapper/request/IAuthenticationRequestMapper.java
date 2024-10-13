package com.foodcourt.UserMicroservice.adapters.driving.mapper.request;

import com.foodcourt.UserMicroservice.adapters.driving.dto.request.AuthenticationRequest;
import com.foodcourt.UserMicroservice.domain.model.Authentication;

public interface IAuthenticationRequestMapper {
    Authentication toModel(AuthenticationRequest auth);
}
