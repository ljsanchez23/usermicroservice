package com.foodcourt.UserMicroservice.adapters.driving.controller;

import com.foodcourt.UserMicroservice.adapters.driving.dto.request.AuthenticationRequest;
import com.foodcourt.UserMicroservice.adapters.driving.dto.response.AuthenticationResponse;
import com.foodcourt.UserMicroservice.adapters.driving.mapper.request.IAuthenticationRequestMapper;
import com.foodcourt.UserMicroservice.adapters.driving.mapper.response.IAuthenticationResponseMapper;
import com.foodcourt.UserMicroservice.adapters.util.AdaptersConstants;
import com.foodcourt.UserMicroservice.domain.api.IAuthenticationServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AdaptersConstants.LOGIN_URL)
public class AuthenticationController {
    private final IAuthenticationServicePort authServicePort;
    private final IAuthenticationRequestMapper authenticationRequestMapper;
    private final IAuthenticationResponseMapper authenticationResponseMapper;

    public AuthenticationController(IAuthenticationServicePort authServicePort, IAuthenticationRequestMapper authenticationRequestMapper, IAuthenticationResponseMapper authenticationResponseMapper) {
        this.authServicePort = authServicePort;
        this.authenticationRequestMapper = authenticationRequestMapper;
        this.authenticationResponseMapper = authenticationResponseMapper;
    }

    @Operation(summary = AdaptersConstants.AUTHENTICATION_ENDPOINT_SUMMARY, description = AdaptersConstants.AUTHENTICATION_ENDPOINT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = AdaptersConstants.OK, description = AdaptersConstants.AUTHENTICATION_OK_DESCRIPTION,
                    content = @Content(mediaType = AdaptersConstants.JSON, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = AdaptersConstants.BAD_REQUEST, description = AdaptersConstants.AUTHENTICATION_BAD_REQUEST_DESCRIPTION,
                    content = @Content(mediaType = AdaptersConstants.JSON, schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse token = authenticationResponseMapper.toResponse(authServicePort.login(authenticationRequestMapper.toModel(authenticationRequest)));
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
