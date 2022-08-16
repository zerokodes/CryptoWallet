package com.Crypto.Wallet.Crypto.Wallet.repository;

import org.springframework.context.annotation.Configuration;


import lombok.AllArgsConstructor;
;

@Configuration
@AllArgsConstructor
public class DatabaseLoader {
	/**@Autowired
	private UserRepo userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserTokenRepo userTokenRepo;**/

   /**@Bean
    CommandLineRunner initializeDataBase() {
        return args -> {

            Token token1 = Token.builder()
                    .tokenName("BTC")
                    .walletAddress("yxhhfbtidmmmsnhd")
                    .build();
            tokenRepo.save(token1);
            


            User user = User.builder()
                    .email("agu@gmail.com")
                    .password(passwordEncoder.encode("12345"))
                    .keyPhrase("1233kkdjuebbdjjsoei773")
                    .role(Role.ADMIN)
                    //.userToken(userTokenList1)
                    .build();
           
            userRepo.save(user);
            
            

            User user2 = User.builder()
                    .email("laku@gmail.com")
                    .password(passwordEncoder.encode("12345"))
                    .keyPhrase("1233kkdjuebbdjjsoei773")
                    .role(Role.USER)
                    //.userToken(userTokenList2)
                    .build();
            
            userRepo.save(user2);
            
            
            UserCrypto userToken1 = new UserCrypto();
            userToken1.setToken(token1);
            userToken1.setCurrentBalance(10);
            userToken1.setUser(user);
            userTokenRepo.save(userToken1);
            
            UserCrypto userToken2 = new UserCrypto();
            userToken2.setToken(token1);
            userToken2.setCurrentBalance(50);
            userToken2.setUser(user2);
            userTokenRepo.save(userToken2);

            List<UserCrypto> userTokenList1 = new ArrayList<>();
            userTokenList1.add(userToken1);
           
            log.info("token list 1 balance"+userTokenList1.get(0).getCurrentBalance());
            

            List<UserCrypto> userTokenList2 = new ArrayList<>();
            userTokenList2.add(userToken2);
            
           
          
            log.info("token list 2 balance"+userTokenList2.get(0).getCurrentBalance());
            
         
        };
    }**/

}
