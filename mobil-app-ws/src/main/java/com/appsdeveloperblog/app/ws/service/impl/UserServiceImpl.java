package com.appsdeveloperblog.app.ws.service.impl;

import com.appsdeveloperblog.app.ws.repositories.UserRepository;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;
import com.appsdeveloperblog.app.ws.shared.dto.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto user) {

        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new RuntimeException("Record already exists");

            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(user, userEntity);

            String publicUserId = utils.generateUserId(30);
            userEntity.setUserId(publicUserId);
            userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getEncryptedPassword()));

            userEntity.setEmailVerificationStatus(false);

            UserEntity storedUserDetails = userRepository.save(userEntity);

            UserDto returnValue = new UserDto();
            BeanUtils.copyProperties(storedUserDetails, returnValue);


            return returnValue;
        }

    @Override
    public UserDto deleteUser(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
        return null;//User not found
        }
        userRepository.delete(userEntity);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;//User deleted
    }

    @Override
    public UserDto getUser(String userName) {
        return null;
    }

}

