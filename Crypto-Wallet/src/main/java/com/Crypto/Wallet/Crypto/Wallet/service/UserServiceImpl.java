package com.Crypto.Wallet.Crypto.Wallet.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Random;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Crypto.Wallet.Crypto.Wallet.entity.Role;
import com.Crypto.Wallet.Crypto.Wallet.entity.User;
import com.Crypto.Wallet.Crypto.Wallet.repository.UserRepo;
import com.Crypto.Wallet.Crypto.Wallet.security.AuthRequest;
import com.Crypto.Wallet.Crypto.Wallet.security.AuthResponse;
import com.Crypto.Wallet.Crypto.Wallet.security.JwtTokenUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;
    
    @Autowired
    EmailService emailService;

    /**
     * This method is use to register a user to the database
     */
	/**@Override
	public ResponseEntity<?> signup(AuthRequest request) throws IOException {
		User checkForUser = userRepo.findByEmail(request.getEmail());
		if(checkForUser != null) {
			log.info("This email has been used");
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		if(!request.getPassword().matches(request.getConfirmPassword())) {
			log.info("Password mismatch");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    	}
		//User checkForUser = userRepo.findByEmail(request.getEmail());
		User user = new User();
		user.setEmail(request.getEmail());
    	user.setPassword(passwordEncoder.encode(request.getPassword()));
    	user.setRole(Role.USER);
    	user.setKeyPhrase(generateSecretePhrase(12));
    	userRepo.save(user);
		return ResponseEntity.ok().build();
	}**/


    /**
     * This method is use to authorize and authenticate user before login 
     * them in
     */
	@Override
	public ResponseEntity<?> login(AuthRequest request) {
		 try {
             authManager.authenticate(
             new UsernamePasswordAuthenticationToken(
                     request.getEmail(), request.getPassword())
     );
     String accessToken = jwtUtil.generateAccessToken(userRepo.findByEmail(request.getEmail()).getId(), request.getEmail());
     AuthResponse response = new AuthResponse(request.getEmail(), accessToken);
      
     return ResponseEntity.ok().body(response);
      
 } catch (BadCredentialsException ex) {
 	log.info("incorrect data");
     return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
     
 }
	}

    /**
     * This method generates random 12 key phrases for user after signing up
     */
	@Override
	public String[] generateSecretePhrase(int numberOfWords) throws IOException{
		//generateRandomWords(5); 
		 File dictionaryFile = new File("dictionary.txt");

		    // Count the number of lines in the file
		    LineNumberReader lnr = new LineNumberReader(new FileReader(dictionaryFile));
		    lnr.skip(Long.MAX_VALUE);

		    // Instantiate a String[] with the size = number of lines
		    String[] dict = new String[lnr.getLineNumber() + 1];
		    lnr.close();

		    Scanner scanner = new Scanner(dictionaryFile);
		    int wordNumber = 0;

		    while (scanner.hasNextLine()) {
		        String word = scanner.nextLine();
		        if (word.length() >= 2 && !(Character.isUpperCase(word.charAt(0)))) {
		            dict[wordNumber] = word;
		           
		            wordNumber++;
		        }
		    }
		    scanner.close();
		    
		
		
	    String[] randomStrings = new String[numberOfWords];
	    Random random = new Random();
	    for(int i = 0; i < numberOfWords; i++)
	    {
	        randomStrings[i] = new String(dict[random.nextInt(113)]);
	        System.out.println("Phrase "+ i +" is " +randomStrings[i].toString());
	    }
	    
	    return randomStrings;
	}

    /**
     * This method is called when user forgets password,it is use to send users
     * link to generate new password to their mail
     */
	@Override
	public ResponseEntity<?> recoverPassword(AuthRequest request) {
		try {
			User user = userRepo.findByEmail(request.getEmail());
			emailService.sendRetrievePasswordToMail(user.getEmail(),"Password retrieve", user.getPassword());
			return ResponseEntity.ok().build();
		}catch(BadCredentialsException ex) {
			log.info("User not Registered");
		     return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		
	}

	@Override
	public void save(User user) throws IOException {
		
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.USER);
    	user.setKeyPhrase(generateSecretePhrase(12));
		userRepo.save(user);
		
	}

	@Override
	public void signup(User request) throws IOException {
		User checkForUser = userRepo.findByEmail(request.getEmail());
		if(checkForUser != null) {
			log.info("This email has been used");
			//return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		//if(!request.getPassword().matches(request.getConfirmPassword())) {
			log.info("Password mismatch");
           // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    	//}
		//User checkForUser = userRepo.findByEmail(request.getEmail());
		//User user = new User();
		//user.setEmail(request.getEmail());
    	//user.setPassword(passwordEncoder.encode(request.getPassword()));
    	//user.setRole(Role.USER);
    	//user.setKeyPhrase(generateSecretePhrase(12));
    	userRepo.save(request);
		//return ResponseEntity.ok().build();
	}


	

}
