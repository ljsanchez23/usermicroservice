package com.foodcourt.UserMicroservice.configuration;

import com.foodcourt.UserMicroservice.adapters.driven.encoder.EncoderPort;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.adapter.RoleAdapter;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.adapter.UserAdapter;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.foodcourt.UserMicroservice.domain.api.IRoleServicePort;
import com.foodcourt.UserMicroservice.domain.api.IUserServicePort;
import com.foodcourt.UserMicroservice.domain.api.usecase.RoleUseCase;
import com.foodcourt.UserMicroservice.domain.api.usecase.UserUseCase;
import com.foodcourt.UserMicroservice.domain.spi.IEncoderPort;
import com.foodcourt.UserMicroservice.domain.spi.IRolePersistencePort;
import com.foodcourt.UserMicroservice.domain.spi.IUserPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleEntityMapper roleEntityMapper;
    private final IRoleRepository roleRepository;

    public BeanConfiguration(IUserRepository userRepository, IUserEntityMapper userEntityMapper, IRoleEntityMapper roleEntityMapper, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
        this.roleEntityMapper = roleEntityMapper;
        this.roleRepository = roleRepository;
    }

    @Bean
    public IEncoderPort encoder(){
        return new EncoderPort(new BCryptPasswordEncoder());
    }

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IRolePersistencePort rolePersistencePort(){
        return new RoleAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort(), encoder());
    }

    @Bean
    public IRoleServicePort roleServicePort(){
        return new RoleUseCase(rolePersistencePort());
    }
}
