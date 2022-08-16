package com.Crypto.Wallet.Crypto.Wallet.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 *A  POJO class that represents an authentication response
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

	private String email;
    private String accessToken;
}
