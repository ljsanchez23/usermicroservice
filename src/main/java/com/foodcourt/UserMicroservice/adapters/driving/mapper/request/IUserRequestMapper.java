package com.foodcourt.UserMicroservice.adapters.driving.mapper.request;

import com.foodcourt.UserMicroservice.adapters.driving.dto.request.UserRequest;
import com.foodcourt.UserMicroservice.domain.model.User;

public interface IUserRequestMapper {
    User toModel(UserRequest userRequest);
}
