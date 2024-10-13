package com.foodcourt.UserMicroservice.configuration.util;

import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.foodcourt.UserMicroservice.domain.spi.IEncoderPort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataFactory {

    private final IEncoderPort encoderPort;

    public DataFactory(IEncoderPort encoderPort) {
        this.encoderPort = encoderPort;
    }

    public static final Long ADMIN_ROLE_ID = 1L;
    public static final String ADMIN_ROLE_NAME = "ROLE_ADMIN";

    public static final Long OWNER_ROLE_ID = 2L;
    public static final String OWNER_ROLE_NAME = "ROLE_OWNER";

    public static final RoleEntity ADMIN_ROLE = new RoleEntity(ADMIN_ROLE_ID, ADMIN_ROLE_NAME);
    public static final RoleEntity OWNER_ROLE = new RoleEntity(OWNER_ROLE_ID, OWNER_ROLE_NAME);

    public static final Long USER_ID = 1L;
    public static final String USER_NAME = "Admin ";
    public static final String USER_LAST_NAME = "admin";
    public static final Integer USER_ID_DOCUMENT = 123456789;
    public static final String USER_PHONE = "+1234567890";
    public static final LocalDate USER_DATE_OF_BIRTH = LocalDate.of(1990, 1, 1);
    public static final String USER_EMAIL = "admin@foodcourt.com";
    public static final String USER_PASSWORD = "admin";

    public UserEntity createDefaultUser() {
        String encodedPassword = encoderPort.encode(USER_PASSWORD);
        return new UserEntity(
                USER_ID,
                USER_NAME,
                USER_LAST_NAME,
                USER_ID_DOCUMENT,
                USER_PHONE,
                USER_DATE_OF_BIRTH,
                USER_EMAIL,
                encodedPassword,
                ADMIN_ROLE
        );
    }
}
