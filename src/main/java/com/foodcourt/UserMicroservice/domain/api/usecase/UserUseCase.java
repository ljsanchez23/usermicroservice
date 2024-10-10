package com.foodcourt.UserMicroservice.domain.api.usecase;

import com.foodcourt.UserMicroservice.domain.api.IUserServicePort;
import com.foodcourt.UserMicroservice.domain.exception.UserAlreadyExistsException;
import com.foodcourt.UserMicroservice.domain.model.User;
import com.foodcourt.UserMicroservice.domain.spi.IEncoderPort;
import com.foodcourt.UserMicroservice.domain.spi.IUserPersistencePort;
import com.foodcourt.UserMicroservice.domain.util.Constants;
import com.foodcourt.UserMicroservice.domain.util.DefaultRoles;
import com.foodcourt.UserMicroservice.domain.util.Validator;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IEncoderPort encoderPort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IEncoderPort encoderPort){
        this.userPersistencePort = userPersistencePort;
        this.encoderPort = encoderPort;
    }

    @Override
    public void saveUser(User user){
        if(userPersistencePort.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException(Constants.USER_ALREADY_EXISTS);
        }
        if(user.getRoleId() == null){
            user.setRoleId(DefaultRoles.OWNER_ID);
        }

        Validator.validateUser(user);
        String encryptedPassword = encoderPort.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userPersistencePort.saveUser(user);
    }
}
