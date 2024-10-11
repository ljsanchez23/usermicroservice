package com.foodcourt.UserMicroservice.domain.util;

import java.util.regex.Pattern;

public class Constants {
    public static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    public static final String PHONE_REGEX = "^\\+?[0-9]{1,12}$";
    public static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
    public static final Integer MINIMUM_AGE = 18;
    public static final String INVALID_EMAIL_FORMAT = "Invalid email format.";
    public static final int PHONE_MAX_LENGTH = 13;
    public static final String PHONE_UNDER_THIRTEEN_CHARACTERS = "Phone number cannot exceed 13 characters.";
    public static final String INVALID_PHONE_FORMAT = "Invalid phone number. It must contain only numbers and optionally a '+' at the start.";
    public static final String NAME_MANDATORY = "Name is mandatory.";
    public static final String LAST_NAME_MANDATORY = "Last name is mandatory.";
    public static final String ID_DOCUMENT_MANDATORY = "Document ID is mandatory.";
    public static final String PHONE_MANDATORY = "Phone number is mandatory.";
    public static final String DOB_MANDATORY = "Date of birth is mandatory.";
    public static final String EMAIL_MANDATORY = "Email is mandatory.";
    public static final String PASSWORD_MANDATORY = "Password is mandatory.";
    public static final int NUMERIC_VALIDATION = 0;
    public static final String IDENTIFICATION_MUST_BE_NUMERIC = "Document ID must be a positive numeric value.";
    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String USER_MUST_BE_ADULT = "User must be adult";
    public static final String USER_NOT_FOUND_ERROR_MESSAGE = "User not found.";
}
