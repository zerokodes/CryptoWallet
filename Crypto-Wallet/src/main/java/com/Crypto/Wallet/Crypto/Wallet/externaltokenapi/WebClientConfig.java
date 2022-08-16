package com.Crypto.Wallet.Crypto.Wallet.externaltokenapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class WebClientConfig {

	   @Bean
	    WebClient webclient() {
	        final int size = 16 * 1024 * 1024;
	        
	        ObjectMapper objectMapper = new ObjectMapper();
	        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	        final ExchangeStrategies strategies = ExchangeStrategies.builder()
	                .codecs(codecs -> {
	                	//codecs.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
	                	codecs.defaultCodecs().maxInMemorySize(size);
	                })
	                		
	                .build();
	        

	        WebClient webClient = WebClient
	                .builder()
	                .baseUrl("https://api.alternative.me/v1/ticker/?limit=20")
	                .defaultCookie("cookieKey", "cookieValue")
	                .exchangeStrategies(strategies)
	                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	                .build();
	        return webClient;
	    }
}
