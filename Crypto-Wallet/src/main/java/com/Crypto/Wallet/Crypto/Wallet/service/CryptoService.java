package com.Crypto.Wallet.Crypto.Wallet.service;

import java.util.List;


import com.Crypto.Wallet.Crypto.Wallet.entity.User;
import com.Crypto.Wallet.Crypto.Wallet.entity.UserCrypto;
import com.Crypto.Wallet.Crypto.Wallet.externaltokenapi.Crypto;

import reactor.core.publisher.Mono;





public interface CryptoService {
	
	void saveToken();
	void autoUpdateCryptoToDB();
	Mono<List<Crypto>> getTokens();
	void sendUserCrypto(UserCrypto senderAddress,UserCrypto receiverAddress, double quantity);
	double convertUserCryptoBalanceToUsd(UserCrypto userCrypto);
	double sumAllUserCryptoBalance(User user);

}
