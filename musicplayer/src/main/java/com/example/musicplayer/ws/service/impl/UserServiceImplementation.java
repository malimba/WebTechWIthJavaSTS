package com.example.musicplayer.ws.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.musicplayer.ws.io.entity.UserEntity;
import com.example.musicplayer.ws.repository.UserRepository;
import com.example.musicplayer.ws.security.CustomUserDetails;
import com.example.musicplayer.ws.service.UserService;
import com.example.musicplayer.ws.shared.dto.UserDto;
import com.example.musicplayer.ws.shared.utils.Utils;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service //declare its a service component
public class UserServiceImplementation implements UserService, UserDetailsService{
    @Autowired
    UserRepository userRepository;

    //utils injectio
    @Autowired
    Utils utils;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
     @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(userEntity);
    }


    @Override
    public UserDto createUser(UserDto user) {
        // Check if user already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists! Login instead.");
        }

        // Create and store user
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setUserId(utils.generateRandomString(30));
        userEntity.setEncryptedPassword(passwordEncoder.encode(user.getPassword()));

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);
        return returnValue;
    }


    @Override
    public UserDto loginUser(UserDto user) {
        //check if user exists
        Optional<UserEntity> userEntityOpt = userRepository.findByEmail(user.getEmail());

        // check if user exists, else throw error
        if (userEntityOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with specified email does not exist!");
        }

        UserEntity userEntity = userEntityOpt.get();

        if(!utils.checkPassword(user.getPassword(), userEntity.getEncryptedPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password incorrect!");
        }

        //preparing return DTO
        UserDto userDto = new UserDto();
        //copy userEntity details to userDto
        BeanUtils.copyProperties(userEntity, userDto);
        return userDto;

        }

    
}