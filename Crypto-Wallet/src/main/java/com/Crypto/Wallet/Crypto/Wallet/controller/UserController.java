package com.Crypto.Wallet.Crypto.Wallet.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Crypto.Wallet.Crypto.Wallet.entity.User;
import com.Crypto.Wallet.Crypto.Wallet.externaltokenapi.Crypto;
import com.Crypto.Wallet.Crypto.Wallet.repository.UserRepo;
import com.Crypto.Wallet.Crypto.Wallet.security.AuthRequest;
import com.Crypto.Wallet.Crypto.Wallet.service.CryptoService;
import com.Crypto.Wallet.Crypto.Wallet.service.UserService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;



@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
	
    @Autowired
    private CryptoService cryptoService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    
    
    
    
    

	
    
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
	    	  
	    	  return userService.login(request);
	    }
	   /** @PostMapping("/signup")
	    public String signup(@RequestBody @Valid User request,Model model) throws IOException{	
	    	userService.signup(request);
	    	return "dashboard2";
	    }**/
	    @RequestMapping(method= RequestMethod.POST,value="/save")
	    public String save(User user, Model model) throws IOException{	
	    	if(user.getEmail().isEmpty() || user.getPassword().isEmpty() || user.getFullName().isEmpty()) {
	    		log.info("input your details");
	    		return "/signup";
	    	}
	    	if(userRepo.findByEmail(user.getEmail()) != null) {
				log.info("This email has been used");
				return "/signup";
			}
	       userService.save(user);
	       return "dashboard2";
	    }
	    @PostMapping("/recover-password")
	    public void recoverPassword(@RequestBody @Valid AuthRequest request) {
	    	userService.recoverPassword(request);
	    }
	    
	    @RequestMapping(method= RequestMethod.GET,value="/homePage")
	    public String homePage() {
	    	return "index-2";
	    }
	    
	    @RequestMapping(method= RequestMethod.GET,value="/loginPage")
	    public String logInPage() {
	    	return "signin";
	    }
	    
	    @RequestMapping(method= RequestMethod.GET,value="/signupPage")
	    public String signUpPage(Model model) {
	    	model.addAttribute("user", new User());
	    	return "signup";
	    }
	    
	    @RequestMapping(method= RequestMethod.GET,value="/forgotPasswordPage")
	    public String forgotPasswordPage() {
	    	return "reset";
	    }
	    
	    @RequestMapping(method= RequestMethod.GET,value="/pricePage")
	    public String pricePage() {
	    	return "price";
	    }
	    
	    @RequestMapping(method= RequestMethod.GET,value="/appPage")
	    public String appPage() {
	    	return "app";
	    }
	    @RequestMapping(method= RequestMethod.GET,value="/aboutPage")
	    public String aboutPage() {
	    	return "about";
	    }
	    @RequestMapping(method= RequestMethod.GET,value="/teamPage")
	    public String teamPage() {
	    	return "team";
	    }
	    @RequestMapping(method= RequestMethod.GET,value="/blogPage")
	    public String blogPage() {
	    	return "blog";
	    }
	    @RequestMapping(method= RequestMethod.GET,value="/careerPage")
	    public String careerPage() {
	    	return "career";
	    }
	    @RequestMapping(method= RequestMethod.GET,value="/contactPage")
	    public String contactPage() {
	    	return "contact";
	    }
	    @RequestMapping(method= RequestMethod.GET,value="/helpDeskPage")
	    public String helpDeskPage() {
	    	return "helpdesk";
	    }
	    @RequestMapping(method= RequestMethod.GET,value="/privacyPage")
	    public String privacyPage() {
	    	return "privacy-policy";
	    }
	    @RequestMapping(method= RequestMethod.GET,value="/faqPage")
	    public String faqPage() {
	    	return "faq";
	    }
	    @RequestMapping(method= RequestMethod.GET,value="/dashboard")
	    public String dashboard() {
	    	return "dashboard2";
	    }
	    @RequestMapping(method= RequestMethod.GET,value="/profile")
	    public String prrofile() {
	    	return "profile2";
	    }
	    @RequestMapping(method= RequestMethod.GET,value="/settings")
	    public String settingsProfile() {
	    	return "settings-profile2";
	    }
	    @RequestMapping(method= RequestMethod.GET,value="/price")
	    public String price() {
	    	return "price2";
	    }
	    @RequestMapping(method= RequestMethod.GET,value="/lock")
	    public String lock() {
	    	return "lock";
	    }
	
}

