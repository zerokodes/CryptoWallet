package com.Crypto.Wallet.Crypto.Wallet.service;

public interface EmailService {
	void sendRetrievePasswordToMail(String receiverEmail,String subject,
			String text);

}
