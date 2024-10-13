package com.foodcourt.UserMicroservice.domain.api.usecase;

import com.foodcourt.UserMicroservice.domain.model.Role;
import com.foodcourt.UserMicroservice.domain.spi.IRolePersistencePort;
import com.foodcourt.UserMicroservice.util.TestConstants;
import com.foodcourt.UserMicroservice.util.TestDataFactory;
import com.foodcourt.UserMicroservice.domain.exception.RoleNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleUseCaseTest {

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @InjectMocks
    private RoleUseCase roleUseCase;

    @Test
    @DisplayName(TestConstants.SHOULD_SAVE_ROLE)
    void shouldSaveRoleSuccessfully() {
        Role role = TestDataFactory.createDefaultRole();

        roleUseCase.saveRole(role);

        verify(rolePersistencePort, times(TestConstants.ONE_INVOCATION)).saveRole(role);
    }

    @Test
    @DisplayName(TestConstants.SHOULD_GET_ROLE_NAME_BY_ID)
    void shouldGetRoleNameByIdSuccessfully() {
        Long roleId = TestConstants.ROLE_ID;
        String expectedRoleName = TestConstants.ROLE_NAME;

        when(rolePersistencePort.getRoleNameById(roleId)).thenReturn(expectedRoleName);

        String actualRoleName = roleUseCase.getRoleNameById(roleId);

        assertEquals(expectedRoleName, actualRoleName);
        verify(rolePersistencePort, times(TestConstants.ONE_INVOCATION)).getRoleNameById(roleId);
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROW_EXCEPTION_WHEN_ROLE_NOT_FOUND)
    void shouldThrowExceptionWhenRoleNotFound() {
        Long roleId = TestConstants.ROLE_ID;

        when(rolePersistencePort.getRoleNameById(roleId)).thenReturn(null);

        assertThrows(RoleNotFoundException.class, () -> roleUseCase.getRoleNameById(roleId));
        verify(rolePersistencePort, times(TestConstants.ONE_INVOCATION)).getRoleNameById(roleId);
    }
}
