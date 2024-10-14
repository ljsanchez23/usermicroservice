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

    public static final Long EMPLOYEE_ROLE_ID = 3L;
    public static final String EMPLOYEE_ROLE_NAME = "ROLE_EMPLOYEE";

    public static final Long CUSTOMER_ROLE_ID = 4L;
    public static final String CUSTOMER_ROLE_NAME = "ROLE_CUSTOMER";

    public static final RoleEntity ADMIN_ROLE = new RoleEntity(ADMIN_ROLE_ID, ADMIN_ROLE_NAME);
    public static final RoleEntity OWNER_ROLE = new RoleEntity(OWNER_ROLE_ID, OWNER_ROLE_NAME);
    public static final RoleEntity EMPLOYEE_ROLE = new RoleEntity(EMPLOYEE_ROLE_ID, EMPLOYEE_ROLE_NAME);
    public static final RoleEntity CUSTOMER_ROLE = new RoleEntity(CUSTOMER_ROLE_ID, CUSTOMER_ROLE_NAME);

    public static final Long ADMIN_USER_ID = 1L;
    public static final String ADMIN_USER_NAME = "Admin ";
    public static final String ADMIN_USER_LAST_NAME = "admin";
    public static final Integer ADMIN_USER_ID_DOCUMENT = 11111111;
    public static final String ADMIN_USER_PHONE = "+111111";
    public static final LocalDate ADMIN_USER_DATE_OF_BIRTH = LocalDate.of(1990, 1, 1);
    public static final String ADMIN_USER_EMAIL = "admin@foodcourt.com";
    public static final String ADMIN_USER_PASSWORD = "admin";

    public static final Long OWNER_USER_ID = 2L;
    public static final String OWNER_USER_NAME = "Owner ";
    public static final String OWNER_USER_LAST_NAME = "owner";
    public static final Integer OWNER_USER_ID_DOCUMENT = 22222222;
    public static final String OWNER_USER_PHONE = "+222222";
    public static final LocalDate OWNER_USER_DATE_OF_BIRTH = LocalDate.of(1980, 1, 1);
    public static final String OWNER_USER_EMAIL = "owner@foodcourt.com";
    public static final String OWNER_USER_PASSWORD = "owner";

    public static final Long EMPLOYEE_USER_ID = 3L;
    public static final String EMPLOYEE_USER_NAME = "Employee ";
    public static final String EMPLOYEE_USER_LAST_NAME = "employee";
    public static final Integer EMPLOYEE_USER_ID_DOCUMENT = 33333333;
    public static final String EMPLOYEE_USER_PHONE = "+33333";
    public static final LocalDate EMPLOYEE_USER_DATE_OF_BIRTH = LocalDate.of(2000, 1, 1);
    public static final String EMPLOYEE_USER_EMAIL = "employee@foodcourt.com";
    public static final String EMPLOYEE_USER_PASSWORD = "employee";

    public UserEntity createAdminUser() {
        String encodedPassword = encoderPort.encode(ADMIN_USER_PASSWORD);
        return new UserEntity(
                ADMIN_USER_ID,
                ADMIN_USER_NAME,
                ADMIN_USER_LAST_NAME,
                ADMIN_USER_ID_DOCUMENT,
                ADMIN_USER_PHONE,
                ADMIN_USER_DATE_OF_BIRTH,
                ADMIN_USER_EMAIL,
                encodedPassword,
                ADMIN_ROLE
        );
    }

    public UserEntity createOwnerUser(){
        String encodedPassword = encoderPort.encode(OWNER_USER_PASSWORD);
        return new UserEntity(
                OWNER_USER_ID,
                OWNER_USER_NAME,
                OWNER_USER_LAST_NAME,
                OWNER_USER_ID_DOCUMENT,
                OWNER_USER_PHONE,
                OWNER_USER_DATE_OF_BIRTH,
                OWNER_USER_EMAIL,
                encodedPassword,
                OWNER_ROLE
        );
    }

    public UserEntity createEmployeeUser(){
        String encodedPassword = encoderPort.encode(EMPLOYEE_USER_PASSWORD);
        return new UserEntity(
                EMPLOYEE_USER_ID,
                EMPLOYEE_USER_NAME,
                EMPLOYEE_USER_LAST_NAME,
                EMPLOYEE_USER_ID_DOCUMENT,
                EMPLOYEE_USER_PHONE,
                EMPLOYEE_USER_DATE_OF_BIRTH,
                EMPLOYEE_USER_EMAIL,
                encodedPassword,
                EMPLOYEE_ROLE
        );
    }
}
