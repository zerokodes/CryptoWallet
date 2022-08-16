package com.Crypto.Wallet.Crypto.Wallet.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.Crypto.Wallet.Crypto.Wallet.externaltokenapi.Crypto;
import com.Crypto.Wallet.Crypto.Wallet.security.AuthRequest;
import com.Crypto.Wallet.Crypto.Wallet.service.CryptoService;
import com.Crypto.Wallet.Crypto.Wallet.service.UserService;


import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;



@RestController
@RequiredArgsConstructor
public class UserController {
	
    @Autowired
    private CryptoService cryptoService;
    @Autowired
    private UserService userService;
    
    

	
    
    @GetMapping("/getTokens")
    public Mono<List<Crypto>>  getCountry1() {
        try {
            return cryptoService.getTokens();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
 
	
	     
	    @PostMapping("/auth/login")
	    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
	      return  userService.login(request);
	    }
	    @PostMapping("/signup")
	    public void signup(@RequestBody @Valid AuthRequest request) throws IOException{	
	    	userService.signup(request);
	    }
	    @PostMapping("/recover-password")
	    public void recoverPassword(@RequestBody @Valid AuthRequest request) {
	    	userService.recoverPassword(request);
	    }
	    
	    
	
}

