package com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.adapter;

import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.foodcourt.UserMicroservice.domain.model.User;
import com.foodcourt.UserMicroservice.domain.spi.IUserPersistencePort;

public class UserAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    public UserAdapter(IUserRepository userRepository, IUserEntityMapper userEntityMapper){
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public void saveUser(User user){
        userRepository.save(userEntityMapper.toEntity(user));
    }
    @Override
    public boolean existsByEmail(String email){
        return userRepository.findByEmail(email).isPresent();
    }
}
