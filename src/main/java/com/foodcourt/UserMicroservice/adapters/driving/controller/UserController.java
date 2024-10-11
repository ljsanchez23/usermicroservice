package com.foodcourt.UserMicroservice.adapters.driving.controller;

import com.foodcourt.UserMicroservice.adapters.driving.dto.request.UserRequest;
import com.foodcourt.UserMicroservice.adapters.driving.mapper.request.IUserRequestMapper;
import com.foodcourt.UserMicroservice.adapters.util.AdaptersConstants;
import com.foodcourt.UserMicroservice.domain.api.IRoleServicePort;
import com.foodcourt.UserMicroservice.domain.api.IUserServicePort;
import com.foodcourt.UserMicroservice.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AdaptersConstants.USER_CONTROLLER_URL)
public class UserController {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IRoleServicePort roleServicePort;

    public UserController(IUserServicePort userServicePort, IUserRequestMapper userRequestMapper, IRoleServicePort roleServicePort) {
        this.userServicePort = userServicePort;
        this.userRequestMapper = userRequestMapper;
        this.roleServicePort = roleServicePort;
    }

    @Operation(summary = AdaptersConstants.ENDPOINT_SUMMARY, description = AdaptersConstants.ENDPOINT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = AdaptersConstants.CREATED, description = AdaptersConstants.CREATED_DESCRIPTION,
            content = @Content(mediaType = AdaptersConstants.JSON, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = AdaptersConstants.BAD_REQUEST, description = AdaptersConstants.BAD_REQUEST_DESCRIPTION,
            content = @Content(mediaType = AdaptersConstants.JSON, schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody UserRequest userRequest){
        User user = userRequestMapper.toModel(userRequest);
        userServicePort.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @RequestMapping(AdaptersConstants.GET_USER_BY_ID_ENDPOINT_URL)
    public ResponseEntity<String> getRoleNameByUserId(@PathVariable(AdaptersConstants.ID_PATH_VARIABLE) Long id){
        Long roleId = userServicePort.findUserById(id).getRoleId();
        String roleName = roleServicePort.getRoleNameById(roleId);
        return ResponseEntity.ok(roleName);
    }
}
