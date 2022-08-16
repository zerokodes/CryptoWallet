package com.Crypto.Wallet.Crypto.Wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Crypto.Wallet.Crypto.Wallet.entity.UserCrypto;

public interface UserTokenRepo extends JpaRepository<UserCrypto, Integer> {

}
