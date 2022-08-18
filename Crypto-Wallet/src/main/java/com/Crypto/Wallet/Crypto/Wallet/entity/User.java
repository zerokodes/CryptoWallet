package com.Crypto.Wallet.Crypto.Wallet.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * This is the user entity(model) class responsible for 
 * keeping user record
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private int id;
	
	@Column(name="_email")
	private String email;
	
	@Column(name="_Password")
	private String password;
	
	private String walletAddress;
	
	@OneToMany(mappedBy = "user")
	private List<UserCrypto> userCrypto;

	@Enumerated(EnumType.STRING)
	private Role role;
	
	private String[] keyPhrase;
	
	private String fullName;
	
	private String currentAddress;
	
	private String permanentAddress;
	
	private String city;
	
	private String postalCode;
	
	private String country;
	
	private Date dateOfBirth;
	
	private Date joinDate;
	
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
	private IdentityVerification identity;

	

	
	
	
	

}
