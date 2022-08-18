package com.Crypto.Wallet.Crypto.Wallet.service;

import java.io.IOException;


import org.springframework.http.ResponseEntity;

import com.Crypto.Wallet.Crypto.Wallet.entity.User;
import com.Crypto.Wallet.Crypto.Wallet.security.AuthRequest;

public interface UserService {
	
    //ResponseEntity<?> signup(AuthRequest request) throws IOException;
    void signup(User request) throws IOException;
    ResponseEntity<?> login( AuthRequest request);
    String[] generateSecretePhrase(int numberOfWords)throws IOException;
    ResponseEntity<?> recoverPassword(AuthRequest request);
    void save(User user) throws IOException;

}
