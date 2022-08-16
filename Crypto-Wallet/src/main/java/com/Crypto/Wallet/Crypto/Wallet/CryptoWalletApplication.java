package com.Crypto.Wallet.Crypto.Wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;




@SpringBootApplication
@EnableScheduling
public class CryptoWalletApplication{
	
	/**@Autowired
	private EmailService emailService;**/

	public static void main(String[] args) {
		SpringApplication.run(CryptoWalletApplication.class, args);
		
	}
	
	/**@EventListener(ApplicationReadyEvent.class)
	public void sendEmail() {
		emailService.sendRetrievePasswordToMail("nduka.henrychisom@gmail.com", "Email sender text", "foo");
	}**/


}
