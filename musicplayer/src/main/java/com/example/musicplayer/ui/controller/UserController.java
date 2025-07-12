package com.example.musicplayer.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.musicplayer.ui.model.request.UserDetailsRequestModel;
import com.example.musicplayer.ui.model.response.UserRest;
import com.example.musicplayer.ws.service.UserService;
import com.example.musicplayer.ws.shared.dto.UserDto;

@RestController
@RequestMapping("users") // maps to http://localhost:8080/users
public class UserController {

    @Autowired
    private UserService userService;


    

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userRequestDetails) {

        // Validate password
        if (!userRequestDetails.getPassword().equals(userRequestDetails.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match");
        }

        // Prepare DTO
        UserDto userDto = new UserDto();
        userDto.setFirstName(userRequestDetails.getFirstName());
        userDto.setLastName(userRequestDetails.getLastName());
        userDto.setEmail(userRequestDetails.getEmail());
        userDto.setPassword(userRequestDetails.getPassword());

        // Create user
        UserDto createdUser = userService.createUser(userDto);

        // Prepare response
        UserRest returnValue = new UserRest();
        returnValue.setFirstName(createdUser.getFirstName());
        returnValue.setLastName(createdUser.getLastName());
        returnValue.setEmail(createdUser.getEmail());
        returnValue.setUserId(createdUser.getUserId());

        return returnValue;
    }
}
