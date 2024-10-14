package com.foodcourt.UserMicroservice.domain.api.usecase;

import com.foodcourt.UserMicroservice.domain.api.IUserServicePort;
import com.foodcourt.UserMicroservice.domain.exception.InvalidDateOfBirthException;
import com.foodcourt.UserMicroservice.domain.exception.UserAlreadyExistsException;
import com.foodcourt.UserMicroservice.domain.exception.UserNotFoundException;
import com.foodcourt.UserMicroservice.domain.model.User;
import com.foodcourt.UserMicroservice.domain.spi.IEncoderPort;
import com.foodcourt.UserMicroservice.domain.spi.IUserPersistencePort;
import com.foodcourt.UserMicroservice.domain.util.Constants;
import com.foodcourt.UserMicroservice.domain.util.Validator;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IEncoderPort encoderPort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IEncoderPort encoderPort){
        this.userPersistencePort = userPersistencePort;
        this.encoderPort = encoderPort;
    }

    @Override
    public void saveUser(String roleName, User user){
        if(userPersistencePort.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException(Constants.USER_ALREADY_EXISTS);
        }
        if(roleName.equals(Constants.ROLE_ADMIN)){
            user.setRoleId(Constants.OWNER_ROLE_ID);
            if (user.getDateOfBirth() == null) {
                throw new InvalidDateOfBirthException(Constants.INVALID_DATE_OF_BIRTH);
            }
        } else if(roleName.equals(Constants.ROLE_OWNER)){
            user.setRoleId(Constants.EMPLOYEE_ROLE_ID);
        }

        Validator.validateUser(user);
        String encryptedPassword = encoderPort.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userPersistencePort.saveUser(user);
    }

    @Override
    public User findUserById(Long id){
        return userPersistencePort.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND_ERROR_MESSAGE));
    }

    @Override
    public void saveCustomer(User user){
        Validator.validateUser(user);
        String encryptedPassword = encoderPort.encode(user.getPassword());
        user.setRoleId(Constants.CUSTOMER_ROLE_ID);
        user.setPassword(encryptedPassword);
        userPersistencePort.saveUser(user);
    }
}
