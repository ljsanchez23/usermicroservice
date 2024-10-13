package com.foodcourt.UserMicroservice.domain.api.usecase;

import com.foodcourt.UserMicroservice.domain.api.IAuthenticationServicePort;
import com.foodcourt.UserMicroservice.domain.exception.InvalidPasswordException;
import com.foodcourt.UserMicroservice.domain.exception.InvalidUsernameException;
import com.foodcourt.UserMicroservice.domain.model.Authentication;
import com.foodcourt.UserMicroservice.domain.model.User;
import com.foodcourt.UserMicroservice.domain.spi.IEncoderPort;
import com.foodcourt.UserMicroservice.domain.spi.ITokenPort;
import com.foodcourt.UserMicroservice.domain.spi.IUserPersistencePort;
import com.foodcourt.UserMicroservice.domain.util.Constants;
import com.foodcourt.UserMicroservice.domain.util.Validator;

public class AuthenticationUseCase implements IAuthenticationServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IEncoderPort userPasswordEncoder;
    private final ITokenPort tokenPort;

    public AuthenticationUseCase(IUserPersistencePort userPersistencePort, IEncoderPort userPasswordEncoder, ITokenPort tokenPort){
        this.userPersistencePort = userPersistencePort;
        this.userPasswordEncoder = userPasswordEncoder;
        this.tokenPort = tokenPort;
    }

    @Override
    public Authentication login(Authentication authentication) {
        Validator.validateAuthentication(authentication);
        User validUser = userPersistencePort.findByEmail(authentication.getUsername());
        if (validUser == null) {
            throw new InvalidUsernameException(Constants.USER_NOT_FOUND);
        }
        boolean passwordMatches = userPasswordEncoder.matches(authentication.getPassword(), validUser.getPassword());
        if (!passwordMatches) {
            throw new InvalidPasswordException(Constants.INVALID_PASSWORD);
        }
        authentication.setPassword(null);
        authentication.setToken(tokenPort.getToken(validUser.getEmail()));
        return authentication;
    }

}
