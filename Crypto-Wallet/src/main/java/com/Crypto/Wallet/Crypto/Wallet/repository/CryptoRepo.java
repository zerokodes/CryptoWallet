package com.Crypto.Wallet.Crypto.Wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Crypto.Wallet.Crypto.Wallet.externaltokenapi.Crypto;

public interface CryptoRepo extends JpaRepository<Crypto, String> {

}
