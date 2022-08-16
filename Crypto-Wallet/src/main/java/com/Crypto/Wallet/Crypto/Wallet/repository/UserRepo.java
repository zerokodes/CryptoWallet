package com.Crypto.Wallet.Crypto.Wallet.repository;
 

import org.springframework.data.jpa.repository.JpaRepository;

import com.Crypto.Wallet.Crypto.Wallet.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
    User findByEmail(String email);
}
