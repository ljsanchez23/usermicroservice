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
        if(!roleRepository.existsById(DataFactory.EMPLOYEE_ROLE.getId())){
            roleRepository.save(DataFactory.EMPLOYEE_ROLE);
        }
        UserEntity adminUser = dataFactory.createAdminUser();
        UserEntity ownerUser = dataFactory.createOwnerUser();
        UserEntity employeeUser = dataFactory.createEmployeeUser();
        if (!userRepository.existsByEmail(adminUser.getEmail())) {
            userRepository.save(adminUser);
        }
        if (!userRepository.existsByEmail(ownerUser.getEmail())) {
            userRepository.save(ownerUser);
        }
        if (!userRepository.existsByEmail(employeeUser.getEmail())) {
            userRepository.save(employeeUser);
        }
    }
}
