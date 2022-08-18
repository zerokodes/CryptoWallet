package com.Crypto.Wallet.Crypto.Wallet.security;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 *A POJO class that abstracts an authentication request
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
	
	@NotNull @Email @Length(min = 5, max = 50)
    private String email;
     
    @NotNull @Length(min = 5, max = 10)
    private String password;
    
    private String confirmPassword;
    
    private String fullName;

}
