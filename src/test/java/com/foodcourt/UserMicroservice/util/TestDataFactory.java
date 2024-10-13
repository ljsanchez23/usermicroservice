package com.foodcourt.UserMicroservice.util;

import com.foodcourt.UserMicroservice.domain.model.Authentication;
import com.foodcourt.UserMicroservice.domain.model.Role;
import com.foodcourt.UserMicroservice.domain.model.User;
import com.foodcourt.UserMicroservice.configuration.util.DataFactory;


public class TestDataFactory {

    public static User createDefaultUser() {
        return new User(TestConstants.USER_ID, TestConstants.USER_NAME, TestConstants.USER_LAST_NAME, TestConstants.USER_ID_DOCUMENT, TestConstants.USER_PHONE,
                TestConstants.USER_DOB, TestConstants.USER_EMAIL, TestConstants.USER_PASSWORD, null);
    }

    public static User createUserWithoutRole() {
        User user = createDefaultUser();
        user.setRoleId(null);
        return user;
    }

    public static Authentication createDefaultAuthentication(){
        return new Authentication(TestConstants.AUTHENTICATION_USERNAME, TestConstants.AUTHENTICATION_RAW_PASSWORD, null);
    }

    public static Role createDefaultRole() {
        return new Role(DataFactory.OWNER_ROLE_ID, DataFactory.OWNER_ROLE_NAME);
    }
}
