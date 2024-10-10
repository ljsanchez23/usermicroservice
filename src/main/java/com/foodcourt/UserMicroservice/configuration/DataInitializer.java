package com.foodcourt.UserMicroservice.configuration;

import com.foodcourt.UserMicroservice.domain.api.IRoleServicePort;
import com.foodcourt.UserMicroservice.domain.util.DefaultRoles;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final IRoleServicePort roleServicePort;

    public DataInitializer(IRoleServicePort roleServicePort) {
        this.roleServicePort = roleServicePort;
    }

    @PostConstruct
    public void init(){
        roleServicePort.saveRole(DefaultRoles.ADMIN);
        roleServicePort.saveRole(DefaultRoles.OWNER);
    }
}
