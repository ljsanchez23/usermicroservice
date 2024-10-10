package com.foodcourt.UserMicroservice.domain.api.usecase;

import com.foodcourt.UserMicroservice.domain.model.Role;
import com.foodcourt.UserMicroservice.domain.spi.IRolePersistencePort;
import com.foodcourt.UserMicroservice.util.TestConstants;
import com.foodcourt.UserMicroservice.util.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

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

}
