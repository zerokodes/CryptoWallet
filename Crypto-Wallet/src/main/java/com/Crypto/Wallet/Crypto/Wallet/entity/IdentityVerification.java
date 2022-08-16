package com.Crypto.Wallet.Crypto.Wallet.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * This is the Identity Verification entity(model) class responsible for 
 * keeping user record
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdentityVerification {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private int id;
	
	private String idType;
	
	private Date issueDate;
	
	private Date expirationDate;
	
	private String idImageFront;
	
	private String idImageBack;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

}
