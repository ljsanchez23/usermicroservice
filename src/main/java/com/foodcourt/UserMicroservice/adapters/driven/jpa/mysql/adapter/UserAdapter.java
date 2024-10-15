package com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.adapter;

import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.foodcourt.UserMicroservice.domain.exception.UserNotFoundException;
import com.foodcourt.UserMicroservice.domain.model.User;
import com.foodcourt.UserMicroservice.domain.spi.IUserPersistencePort;

import java.util.Optional;

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
    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id)
                .map(userEntityMapper::toModel);
    }
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userEntityMapper::toModel)
                .orElse(null);
    }

    @Override
    public User saveEmployee(User user) {
        // Guarda el usuario en la base de datos
        UserEntity savedUserEntity = userRepository.save(userEntityMapper.toEntity(user));

        // Devuelve el usuario mapeado con su ID
        return userEntityMapper.toModel(savedUserEntity);
    }
}
