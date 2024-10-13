package com.foodcourt.UserMicroservice.domain.api.usecase;

import com.foodcourt.UserMicroservice.domain.exception.UserAlreadyExistsException;
import com.foodcourt.UserMicroservice.domain.exception.UserNotFoundException;
import com.foodcourt.UserMicroservice.domain.model.User;
import com.foodcourt.UserMicroservice.domain.spi.IEncoderPort;
import com.foodcourt.UserMicroservice.domain.spi.IUserPersistencePort;
import com.foodcourt.UserMicroservice.domain.util.Constants;
import com.foodcourt.UserMicroservice.configuration.util.DataFactory;
import com.foodcourt.UserMicroservice.util.TestConstants;
import com.foodcourt.UserMicroservice.util.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;
    @Mock
    private IEncoderPort encoderPort;

    @InjectMocks
    private UserUseCase userUseCase;

    @Test
    @DisplayName(TestConstants.USER_ALREADY_EXISTS_EXCEPTION)
    void shouldThrowExceptionWhenUserEmailAlreadyExists() {
        User user = TestDataFactory.createDefaultUser();
        user.setEmail(TestConstants.VALID_EMAIL);

        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(true);

        UserAlreadyExistsException thrown =
                assertThrows(UserAlreadyExistsException.class, () -> userUseCase.saveUser(user));

        assertEquals(Constants.USER_ALREADY_EXISTS, thrown.getMessage());
    }

    @Test
    @DisplayName(TestConstants.SHOULD_SET_DEFAULT_ROLE)
    void shouldSetDefaultRoleWhenNoRoleIsProvided() {
        User user = TestDataFactory.createUserWithoutRole();
        user.setPassword(TestConstants.VALID_PASSWORD);

        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(false);
        when(encoderPort.encode(user.getPassword())).thenReturn(TestConstants.VALID_PASSWORD);

        userUseCase.saveUser(user);

        verify(userPersistencePort).saveUser(user);
        assertNotNull(user.getRoleId());
        assertEquals(DataFactory.OWNER_ROLE_ID, user.getRoleId());
    }

    @Test
    @DisplayName(TestConstants.SHOULD_FIND_USER_BY_ID)
    void shouldFindUserByIdSuccessfully() {
        Long userId = TestConstants.USER_ID;
        User expectedUser = TestDataFactory.createDefaultUser();

        when(userPersistencePort.findUserById(userId)).thenReturn(Optional.of(expectedUser));

        User actualUser = userUseCase.findUserById(userId);

        assertEquals(expectedUser, actualUser);
        verify(userPersistencePort, times(TestConstants.ONE_INVOCATION)).findUserById(userId);
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROW_EXCEPTION_WHEN_USER_NOT_FOUND)
    void shouldThrowExceptionWhenUserNotFound() {
        Long userId = TestConstants.USER_ID;

        when(userPersistencePort.findUserById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userUseCase.findUserById(userId));
        verify(userPersistencePort, times(TestConstants.ONE_INVOCATION)).findUserById(userId);
    }
}
