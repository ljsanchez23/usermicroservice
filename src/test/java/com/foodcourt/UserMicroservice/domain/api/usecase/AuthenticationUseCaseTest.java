package com.foodcourt.UserMicroservice.domain.api.usecase;

import com.foodcourt.UserMicroservice.domain.exception.InvalidPasswordException;
import com.foodcourt.UserMicroservice.domain.exception.InvalidUsernameException;
import com.foodcourt.UserMicroservice.domain.model.Authentication;
import com.foodcourt.UserMicroservice.domain.model.User;
import com.foodcourt.UserMicroservice.domain.spi.IEncoderPort;
import com.foodcourt.UserMicroservice.domain.spi.ITokenPort;
import com.foodcourt.UserMicroservice.domain.spi.IUserPersistencePort;
import com.foodcourt.UserMicroservice.domain.util.Constants;
import com.foodcourt.UserMicroservice.util.TestConstants;
import com.foodcourt.UserMicroservice.util.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IEncoderPort userPasswordEncoder;

    @Mock
    private ITokenPort tokenPort;

    @InjectMocks
    private AuthenticationUseCase authUseCase;

    @Test
    @DisplayName(TestConstants.AUTHENTICATION_ACCESS)
    void shouldAccessWithCorrectCredentials() {

        Authentication authenticationRequest = TestDataFactory.createDefaultAuthentication();

        User mockUser = TestDataFactory.createDefaultUser();
        String encodedPassword = mockUser.getPassword();

        when(userPersistencePort.findByEmail(TestConstants.AUTHENTICATION_USERNAME)).thenReturn(mockUser);
        when(userPasswordEncoder.matches(TestConstants.AUTHENTICATION_RAW_PASSWORD, encodedPassword)).thenReturn(true);
        when(tokenPort.getToken(TestConstants.AUTHENTICATION_USERNAME)).thenReturn(TestConstants.VALID_TOKEN);

        Authentication result = authUseCase.login(authenticationRequest);

        assertNotNull(result);
        assertEquals(TestConstants.VALID_TOKEN, result.getToken());
        assertNull(result.getPassword());

        verify(userPersistencePort).findByEmail(TestConstants.AUTHENTICATION_USERNAME);
        verify(userPasswordEncoder).matches(TestConstants.AUTHENTICATION_RAW_PASSWORD, encodedPassword);
        verify(tokenPort).getToken(TestConstants.AUTHENTICATION_USERNAME);
    }

    @Test
    @DisplayName(TestConstants.USER_DOES_NOT_EXISTS)
    void shouldThrowExceptionWhenUserDoesNotExists() {
        Authentication authenticationRequest = new Authentication(TestConstants.ADMIN_EMAIL, TestConstants.ADMIN_PASSWORD, null);

        when(userPersistencePort.findByEmail(TestConstants.ADMIN_EMAIL)).thenReturn(null);

        Exception exception = assertThrows(InvalidUsernameException.class, () -> {
            authUseCase.login(authenticationRequest);
        });
        String expectedMessage = TestConstants.USER_NOT_FOUND;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        verify(userPersistencePort).findByEmail(TestConstants.ADMIN_EMAIL);
        verify(userPasswordEncoder, never()).matches(anyString(), anyString());
        verify(tokenPort, never()).getToken(anyString());
    }

    @Test
    @DisplayName(TestConstants.INCORRECT_PASSWORD_EXCEPTION)
    void shouldThrowExceptionWhenPasswordIsIncorrect() {
        Authentication authenticationRequest = TestDataFactory.createDefaultAuthentication();
        authenticationRequest.setPassword(TestConstants.AUTHENTICATION_WRONG_PASSWORD);

        User mockUser = TestDataFactory.createDefaultUser();
        String encodedPassword = mockUser.getPassword();

        when(userPersistencePort.findByEmail(TestConstants.AUTHENTICATION_USERNAME)).thenReturn(mockUser);
        when(userPasswordEncoder.matches(TestConstants.AUTHENTICATION_WRONG_PASSWORD, encodedPassword)).thenReturn(false);

        Exception exception = assertThrows(InvalidPasswordException.class, () -> {
            authUseCase.login(authenticationRequest);
        });

        String expectedMessage = Constants.INVALID_PASSWORD;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        verify(userPersistencePort).findByEmail(TestConstants.AUTHENTICATION_USERNAME);
        verify(userPasswordEncoder).matches(TestConstants.AUTHENTICATION_WRONG_PASSWORD, encodedPassword);
        verify(tokenPort, never()).getToken(anyString());
    }
}
