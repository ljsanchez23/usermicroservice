package com.foodcourt.UserMicroservice.domain.api;

import com.foodcourt.UserMicroservice.domain.model.Authentication;

public interface IAuthenticationServicePort {
    Authentication login(Authentication authentication);
}
