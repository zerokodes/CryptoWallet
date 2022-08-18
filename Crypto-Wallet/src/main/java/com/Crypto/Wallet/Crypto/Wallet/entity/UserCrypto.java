package com.Crypto.Wallet.Crypto.Wallet.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.Crypto.Wallet.Crypto.Wallet.externaltokenapi.Crypto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserCrypto {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private int id;
	@ManyToOne
    @JoinColumn(name="crypto_id")
	private Crypto crypto;
	
	private double currentBalance;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	

}
