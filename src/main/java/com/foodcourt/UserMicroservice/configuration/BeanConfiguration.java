package com.foodcourt.UserMicroservice.configuration;

import com.foodcourt.UserMicroservice.adapters.driven.encoder.EncoderAdapter;
import com.foodcourt.UserMicroservice.adapters.driven.feign.adapter.FoodCourtAdapter;
import com.foodcourt.UserMicroservice.adapters.driven.feign.client.IFoodCourtFeignClient;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.adapter.RoleAdapter;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.adapter.UserAdapter;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.foodcourt.UserMicroservice.adapters.driven.token.jwt.JwtAdapter;
import com.foodcourt.UserMicroservice.adapters.driving.mapper.request.IEmployeeRequestMapper;
import com.foodcourt.UserMicroservice.domain.api.IAuthenticationServicePort;
import com.foodcourt.UserMicroservice.domain.api.IRoleServicePort;
import com.foodcourt.UserMicroservice.domain.api.IUserServicePort;
import com.foodcourt.UserMicroservice.domain.api.usecase.AuthenticationUseCase;
import com.foodcourt.UserMicroservice.domain.api.usecase.RoleUseCase;
import com.foodcourt.UserMicroservice.domain.api.usecase.UserUseCase;
import com.foodcourt.UserMicroservice.domain.spi.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleEntityMapper roleEntityMapper;
    private final IRoleRepository roleRepository;
    private final IFoodCourtFeignClient foodCourtFeignClient;
    private final IEmployeeRequestMapper addEmployeeRequestMapper;

    public BeanConfiguration(IUserRepository userRepository, IUserEntityMapper userEntityMapper, IRoleEntityMapper roleEntityMapper, IRoleRepository roleRepository, IFoodCourtFeignClient foodCourtFeignClient, IEmployeeRequestMapper addEmployeeRequestMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
        this.roleEntityMapper = roleEntityMapper;
        this.roleRepository = roleRepository;
        this.foodCourtFeignClient = foodCourtFeignClient;
        this.addEmployeeRequestMapper = addEmployeeRequestMapper;
    }

    @Bean
    public IFoodCourtPort foodCourtPort(){return new FoodCourtAdapter(foodCourtFeignClient, addEmployeeRequestMapper);
    }
    @Bean
    public IEncoderPort encoder(){
        return new EncoderAdapter(new BCryptPasswordEncoder());
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
        return new UserUseCase(userPersistencePort(), encoder(), foodCourtPort());
    }

    @Bean
    public IRoleServicePort roleServicePort(){
        return new RoleUseCase(rolePersistencePort());
    }

    @Bean
    public ITokenPort tokenPort(){ return new JwtAdapter(userRepository, userEntityMapper, roleRepository);
    }

    @Bean
    public IAuthenticationServicePort authServicePort(){ return new AuthenticationUseCase(userPersistencePort(), encoder(), tokenPort());}

}
