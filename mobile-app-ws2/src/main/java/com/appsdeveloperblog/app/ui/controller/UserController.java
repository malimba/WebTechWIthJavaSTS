package com.appsdeveloperblog.app.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ui.model.response.UserRest;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping
	public String getUser() {
		return "get user was called";
	}
	
	
	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
	    UserDto userDto = new UserDto();
	    userDto.setFirstName(userDetails.getFirstName());
	    userDto.setLastName(userDetails.getLastName());
	    userDto.setEmail(userDetails.getEmail());
	    userDto.setPassword(userDetails.getPassword());

	    UserDto createdUser = userService.createUser(userDto);

	    UserRest returnValue = new UserRest();
	    returnValue.setFirstName(createdUser.getFirstName());
	    returnValue.setLastName(createdUser.getLastName());
	    returnValue.setEmail(createdUser.getEmail());
	    returnValue.setUserId(createdUser.getUserId());
	    return returnValue;
	}

	@PutMapping
	public String updateUser() {
		return "update user was called";
	}
	@DeleteMapping   
	public String deleteUser() {
		return "delete user was called";
	}
}
