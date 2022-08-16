package com.Crypto.Wallet.Crypto.Wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
	
	 @Autowired
	 private JavaMailSender emailSender;

	@Override
	public void sendRetrievePasswordToMail(String receiverEmail, String subject, 
			String text) {
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("henrysam140@gmail.com");
        message.setTo(receiverEmail); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
        log.info("Email sent");
		
	}

	

	

}
