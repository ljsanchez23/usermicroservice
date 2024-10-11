package com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.mapper;

import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.foodcourt.UserMicroservice.domain.model.User;


public interface IUserEntityMapper {
    UserEntity toEntity(User user);
    User toModel(UserEntity userEntity);
}
