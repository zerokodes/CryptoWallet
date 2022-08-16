package com.Crypto.Wallet.Crypto.Wallet.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import com.Crypto.Wallet.Crypto.Wallet.entity.User;
import com.Crypto.Wallet.Crypto.Wallet.entity.UserCrypto;
import com.Crypto.Wallet.Crypto.Wallet.externaltokenapi.Crypto;
import com.Crypto.Wallet.Crypto.Wallet.repository.CryptoRepo;


import lombok.extern.slf4j.Slf4j;

import reactor.core.publisher.Mono;


@Service
@Slf4j
@Component
public class CryptoServiceImpl implements CryptoService {
	
	
	
	@Autowired
	private CryptoRepo cryptoRepo;

	@Autowired
	private WebClient webClient;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Override
	public void saveToken() {
		
		
	}

	@Override
	public Mono<List<Crypto>>  getTokens() {
		List<Crypto> cryptos;
		Mono<List<Crypto>> tokens;
		tokens =  webClient
				.get()
				.uri("https://api.alternative.me/v1/ticker/?limit=20")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Crypto>>() {});
		cryptos = tokens.block();
		cryptoRepo.saveAll(cryptos);
		log.info( "quotes list"+cryptos.stream()
				  .map(Crypto::getMarketCapUsd)
				  .collect(Collectors.toList()));
				  //.bodyToFlux(Crypto.class)
				//.collectList()
				///doOnNext(r -> {
					
					//log.info("These are the id for btc"+r.get(0).getName());
				// cryptos = mapper.readValue(r, new TypeReference<List<Crypto>>(){});
					
				//})
				//.map(response -> {
					
				//cryptos = response;})
				
				
				//.log();
		
		
		
				//.doOnError(throwable -> logger.error("Failed for some reason", throwable));
		return tokens;
		
		
		
				
	}

	//everyday update = 86400000
	@Override
	@Scheduled(fixedRate = 5000 * 100)
	public void autoUpdateCryptoToDB() {
		cryptoRepo.deleteAll();
		getTokens();
		log.info("updated just now" + dateFormat.format(new Date()));
	}

	

	@Override
	public void sendUserCrypto(UserCrypto senderAddress,UserCrypto receiverAddress, double quantity) {
		double senderBalance = senderAddress.getCurrentBalance();
		double receiverBalance = receiverAddress.getCurrentBalance();
		if(quantity > senderBalance) {
			// insufficient fund
		}
		double senderNewBalance = senderBalance - quantity;
		double receiverNewBalance = receiverBalance + quantity;
		senderAddress.setCurrentBalance(senderNewBalance);
		receiverAddress.setCurrentBalance(receiverNewBalance);
		
	}

	@Override
	public double convertUserCryptoBalanceToUsd(UserCrypto userCrypto) {
		double convertPriceFromStringToDoble = Double.parseDouble(userCrypto.getCrypto().getPriceUsd());
		double balanceUsd = userCrypto.getCurrentBalance() * convertPriceFromStringToDoble;
		return balanceUsd;
	}

	@Override
	public double sumAllUserCryptoBalance(User user) {
	double totalCrytoSumInUsd = 0.0;
	for(int i = 0;i < user.getUserCrypto().size();i++){
	    totalCrytoSumInUsd += convertUserCryptoBalanceToUsd(user.getUserCrypto().get(i));
	}
		
		return totalCrytoSumInUsd;
	}

	
}
