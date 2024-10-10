package com.foodcourt.UserMicroservice.util;

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
}
