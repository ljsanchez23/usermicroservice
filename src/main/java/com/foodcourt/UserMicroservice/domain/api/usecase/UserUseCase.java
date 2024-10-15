package com.foodcourt.UserMicroservice.domain.api.usecase;

import com.foodcourt.UserMicroservice.domain.api.IUserServicePort;
import com.foodcourt.UserMicroservice.domain.exception.InvalidDateOfBirthException;
import com.foodcourt.UserMicroservice.domain.exception.UserAlreadyExistsException;
import com.foodcourt.UserMicroservice.domain.exception.UserNotFoundException;
import com.foodcourt.UserMicroservice.domain.model.User;
import com.foodcourt.UserMicroservice.domain.spi.IEncoderPort;
import com.foodcourt.UserMicroservice.domain.spi.IFoodCourtPort;
import com.foodcourt.UserMicroservice.domain.spi.IUserPersistencePort;
import com.foodcourt.UserMicroservice.domain.util.Constants;
import com.foodcourt.UserMicroservice.domain.util.Validator;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IEncoderPort encoderPort;
    private final IFoodCourtPort foodCourtPort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IEncoderPort encoderPort, IFoodCourtPort foodCourtPort) {
        this.userPersistencePort = userPersistencePort;
        this.encoderPort = encoderPort;
        this.foodCourtPort = foodCourtPort;
    }

    @Override
    public void saveUser(String roleName, User user) {
        if (userPersistencePort.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(Constants.USER_ALREADY_EXISTS);
        }
        if (roleName.equals(Constants.ROLE_ADMIN)) {
            user.setRoleId(Constants.OWNER_ROLE_ID);
            if (user.getDateOfBirth() == null) {
                throw new InvalidDateOfBirthException(Constants.INVALID_DATE_OF_BIRTH);
            }
        }
        Validator.validateUser(user);
        String encryptedPassword = encoderPort.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        userPersistencePort.saveUser(user);
    }

    @Override
    public User saveEmployee(User user) {
        if (userPersistencePort.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(Constants.USER_ALREADY_EXISTS);
        }

        user.setRoleId(Constants.EMPLOYEE_ROLE_ID);

        Validator.validateUser(user);
        String encryptedPassword = encoderPort.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        return userPersistencePort.saveEmployee(user);
    }

    @Override
    public void addEmployeeToRestaurant(Long restaurantId, Long employeeId) {
        foodCourtPort.addEmployeeToRestaurant(restaurantId, employeeId);
    }

    @Override
    public void saveCustomer(User user){
        Validator.validateUser(user);
        String encryptedPassword = encoderPort.encode(user.getPassword());
        user.setRoleId(Constants.CUSTOMER_ROLE_ID);
        user.setPassword(encryptedPassword);
        userPersistencePort.saveUser(user);
    }

    @Override
    public User findUserById(Long id){
        return userPersistencePort.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND_ERROR_MESSAGE));
    }


}
