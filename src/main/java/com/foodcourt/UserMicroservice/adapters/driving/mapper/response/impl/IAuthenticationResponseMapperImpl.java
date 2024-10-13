package com.foodcourt.UserMicroservice.adapters.driving.mapper.response.impl;

import com.foodcourt.UserMicroservice.adapters.driving.dto.response.AuthenticationResponse;
import com.foodcourt.UserMicroservice.adapters.driving.mapper.response.IAuthenticationResponseMapper;
import com.foodcourt.UserMicroservice.domain.model.Authentication;
import org.springframework.stereotype.Component;

@Component
public class IAuthenticationResponseMapperImpl implements IAuthenticationResponseMapper {
    @Override
    public AuthenticationResponse toResponse(Authentication authentication){
        String token = authentication.getToken();
        return new AuthenticationResponse(token);
    }
}
