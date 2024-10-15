package com.foodcourt.UserMicroservice.util;

import java.time.LocalDate;

public class TestConstants {
    public static final String MANDATORY_FIELD_NAME_EXCEPTION = "Should throw MandatoryFieldException when name is null";
    public static final String INVALID_EMAIL_EXCEPTION = "Should throw InvalidEmailException when email format is invalid";
    public static final String INVALID_EMAIL = "invalid-email";
    public static final String PHONE_MAX_LENGTH_EXCEPTION = "Should throw InvalidPhoneException when phone exceeds max length";
    public static final String INVALID_PHONE = "123456789012345";
    public static final String INVALID_DOCUMENT_EXCEPTION = "Should throw InvalidDocumentException when idDocument is less than valid numeric value";
    public static final int INVALID_DOCUMENT = -1;
    public static final String UNDERAGE_USER_EXCEPTION = "Should throw UnderageUserException when user is underage";
    public static final int UNDERAGE_USER = 10;
    public static final String SHOULD_NOT_THROW_EXCEPTION = "Should not throw any exception when all fields are valid";
    public static final String USER_ALREADY_EXISTS_EXCEPTION = "Should throw exception when user email already exists";
    public static final String VALID_EMAIL = "test@example.com";
    public static final String SHOULD_SET_DEFAULT_ROLE = "Should set default role when no role is provided";
    public static final String VALID_PASSWORD = "encryptedPassword";
    public static final String SHOULD_SAVE_ROLE = "Should save role successfully";
    public static final int ONE_INVOCATION = 1;
    public static final Long USER_ID = 1L;
    public static final String USER_NAME = "John Without ";
    public static final String USER_LAST_NAME = "Fear";
    public static final Integer USER_ID_DOCUMENT = 1111111;
    public static final String USER_PHONE = "3222222";
    public static final LocalDate USER_DOB = LocalDate.of(1998, 12, 23);
    public static final String USER_EMAIL = "test@example.com";
    public static final String USER_PASSWORD = "password";

    public static final String SHOULD_GET_ROLE_NAME_BY_ID = "Should get the role name by id";
    public static final String SHOULD_THROW_EXCEPTION_WHEN_ROLE_NOT_FOUND = "Should throw exception when role is not found";
    public static final String ROLE_NAME = "TEST ROLE";
    public static final Long ROLE_ID = 1L;
    public static final String SHOULD_FIND_USER_BY_ID = "Should find user by id";
    public static final String SHOULD_THROW_EXCEPTION_WHEN_USER_NOT_FOUND = "Should throw exception when the user is" +
            "not found";
    public static final String AUTHENTICATION_ACCESS = "Login method with correct credentials";
    public static final String USER_DOES_NOT_EXISTS = "Login method when the user does not exist in the system";
    public static final String INCORRECT_PASSWORD_EXCEPTION = "Login method when the password is incorrect";
    public static final String AUTHENTICATION_USERNAME = "test@example.com";
    public static final String AUTHENTICATION_RAW_PASSWORD = "rawPassword";
    public static final String VALID_TOKEN = "validToken";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String ADMIN_EMAIL = "admin@foodcourt.com";
    public static final String ADMIN_PASSWORD = "admin";
    public static final String AUTHENTICATION_WRONG_PASSWORD = "wrongPassword";
    public static final String SHOULD_SAVE_CUSTOMER = "Should save customer with encrypted password and default role";
    public static final Long RESTAURANT_ID = 1L;
    public static final Long EMPLOYEE_ID = 2L;
    public static final String SHOULD_CALL_FOOD_COURT_PORT = "Should call food court port and save the employee";
    public static final String PLAIN_TEXT_PASSWORD = "plaintextpassword";
    public static final int INVOCATION_ARGUMENT = 0;
    public static final String SHOULD_SAVE_EMPLOYEE = "Should save the employee";
    public static final String SHOULD_THROW_EXCEPTION_WHEN_USER_EXISTS = "Should throw exception when user exists";
}
