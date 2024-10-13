package com.foodcourt.UserMicroservice.adapters.driven.token.jwt;

import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.foodcourt.UserMicroservice.adapters.driven.token.util.JwtRoleNotFoundException;
import com.foodcourt.UserMicroservice.adapters.util.AdaptersConstants;
import com.foodcourt.UserMicroservice.configuration.security.util.SecurityConstants;
import com.foodcourt.UserMicroservice.domain.model.User;
import com.foodcourt.UserMicroservice.domain.spi.ITokenPort;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;

public class JwtAdapter implements ITokenPort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;

    public JwtAdapter(IUserRepository userRepository, IUserEntityMapper userEntityMapper, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public String getToken(String email) {

        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(SecurityConstants.JWT_USER_NOT_FOUND + email));

        User user = userEntityMapper.toModel(userEntity);

        Long roleId = user.getRoleId();

        RoleEntity roleEntity = roleRepository.findById(roleId)
                .orElseThrow(() -> new JwtRoleNotFoundException(AdaptersConstants.JWT_ROLE_NOT_FOUND));

        String roleName = roleEntity.getName();
        Long userId = user.getId();

        return Jwts.builder()
                .id(SecurityConstants.JWT_ID)
                .subject(email)
                .claim(SecurityConstants.JWT_AUTHORITY, roleName)
                .claim(SecurityConstants.USER_ID, userId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + SecurityConstants.JWT_TIME_EXPIRATION))
                .signWith(SecurityConstants.getSignedKey(SecurityConstants.JWT_SECRET_KEY))
                .compact();
    }
}
