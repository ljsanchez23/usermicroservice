package com.foodcourt.UserMicroservice.domain.util;

import com.foodcourt.UserMicroservice.domain.exception.*;
import com.foodcourt.UserMicroservice.domain.model.User;
import com.foodcourt.UserMicroservice.util.TestConstants;
import com.foodcourt.UserMicroservice.util.TestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class ValidatorTest {

    private User validUser;

    @BeforeEach
    void setUp() {
        validUser = TestDataFactory.createDefaultUser();
    }

    @Test
    @DisplayName(TestConstants.MANDATORY_FIELD_NAME_EXCEPTION)
    void shouldThrowExceptionWhenNameIsNull() {

        validUser.setName(null);

        assertThrows(MandatoryFieldException.class, () -> Validator.validateUser(validUser));
    }

    @Test
    @DisplayName(TestConstants.INVALID_EMAIL_EXCEPTION)
    void shouldThrowExceptionWhenEmailIsInvalid() {

        validUser.setEmail(TestConstants.INVALID_EMAIL);

        assertThrows(InvalidEmailException.class, () -> Validator.validateUser(validUser));
    }

    @Test
    @DisplayName(TestConstants.PHONE_MAX_LENGTH_EXCEPTION)
    void shouldThrowExceptionWhenPhoneExceedsMaxLength() {
        validUser.setPhone(TestConstants.INVALID_PHONE);

        assertThrows(InvalidPhoneException.class, () -> Validator.validateUser(validUser));
    }

    @Test
    @DisplayName(TestConstants.INVALID_DOCUMENT_EXCEPTION)
    void shouldThrowExceptionWhenIdDocumentIsInvalid() {
        validUser.setIdDocument(TestConstants.INVALID_DOCUMENT);
        assertThrows(InvalidDocumentException.class, () -> Validator.validateUser(validUser));
    }

    @Test
    @DisplayName(TestConstants.UNDERAGE_USER_EXCEPTION)
    void shouldThrowExceptionWhenUserIsUnderage() {
        validUser.setDateOfBirth(LocalDate.now().minusYears(TestConstants.UNDERAGE_USER));

        assertThrows(UnderageUserException.class, () -> Validator.validateUser(validUser));
    }

    @Test
    @DisplayName(TestConstants.SHOULD_NOT_THROW_EXCEPTION)
    void shouldNotThrowExceptionWhenAllFieldsAreValid() {
        assertDoesNotThrow(() -> Validator.validateUser(validUser));
    }
}
