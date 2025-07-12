package com.example.musicplayer.ws.shared.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.mindrot.jbcrypt.BCrypt;


import org.springframework.stereotype.Component;

@Component
public class Utils {
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private final int ITERATIONS = 10000;
	private final int KEY_LENGTH = 256;
	public String generateRandomString(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		
		for (int i=0; i< length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(returnValue);
	}
    public String encrptPassword(String password) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(password, salt);
    }

    public boolean checkPassword(String plainPassword, String hashedPassword){
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
