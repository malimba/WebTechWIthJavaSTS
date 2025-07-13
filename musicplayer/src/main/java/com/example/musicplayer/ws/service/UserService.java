package com.example.musicplayer.ws.service;

import com.example.musicplayer.ws.shared.dto.UserDto; //retrieve user dto




//interface for user service
public interface UserService {
    
    UserDto createUser(UserDto user);
    UserDto loginUser(UserDto user);
}
