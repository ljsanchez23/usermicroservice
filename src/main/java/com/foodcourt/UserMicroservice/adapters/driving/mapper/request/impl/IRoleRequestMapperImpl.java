package com.foodcourt.UserMicroservice.adapters.driving.mapper.request.impl;

import com.foodcourt.UserMicroservice.adapters.driving.dto.request.RoleRequest;
import com.foodcourt.UserMicroservice.adapters.driving.mapper.request.IRoleRequestMapper;
import com.foodcourt.UserMicroservice.domain.model.Role;
import org.springframework.stereotype.Component;

@Component
public class IRoleRequestMapperImpl implements IRoleRequestMapper {
    @Override
    public Role toModel(RoleRequest roleRequest) {
        if (roleRequest == null) {
            return null;
        }
        Long id = roleRequest.getId();
        String name = roleRequest.getName();

        return new Role(id, name);
    }
}
