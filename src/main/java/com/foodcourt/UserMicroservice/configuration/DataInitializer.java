package com.foodcourt.UserMicroservice.configuration;

import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.foodcourt.UserMicroservice.configuration.util.DataFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final DataFactory dataFactory;

    public DataInitializer(IRoleRepository roleRepository, IUserRepository userRepository, DataFactory dataFactory) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.dataFactory = dataFactory;
    }

    @PostConstruct
    public void init() {
        if (!roleRepository.existsById(DataFactory.ADMIN_ROLE.getId())) {
            roleRepository.save(DataFactory.ADMIN_ROLE);
        }
        if (!roleRepository.existsById(DataFactory.OWNER_ROLE.getId())) {
            roleRepository.save(DataFactory.OWNER_ROLE);
        }

        UserEntity defaultUser = dataFactory.createDefaultUser();
        if (!userRepository.existsByEmail(defaultUser.getEmail())) {
            userRepository.save(defaultUser);
        }
    }
}
