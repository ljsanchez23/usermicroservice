package com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.mapper;

import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.foodcourt.UserMicroservice.domain.model.Role;

public interface IRoleEntityMapper {
    RoleEntity toEntity(Role role);
    Role toModel(RoleEntity roleEntity);
}
