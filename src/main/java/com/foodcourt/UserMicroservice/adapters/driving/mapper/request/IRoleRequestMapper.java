package com.foodcourt.UserMicroservice.adapters.driving.mapper.request;

import com.foodcourt.UserMicroservice.adapters.driving.dto.request.RoleRequest;
import com.foodcourt.UserMicroservice.domain.model.Role;

public interface IRoleRequestMapper {
    Role toModel(RoleRequest roleRequest);
}
