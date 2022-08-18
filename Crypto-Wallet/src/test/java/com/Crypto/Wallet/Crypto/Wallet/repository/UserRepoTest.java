package com.Crypto.Wallet.Crypto.Wallet.repository;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import com.Crypto.Wallet.Crypto.Wallet.entity.IdentityVerification;
import com.Crypto.Wallet.Crypto.Wallet.entity.Role;
import com.Crypto.Wallet.Crypto.Wallet.entity.User;
import com.Crypto.Wallet.Crypto.Wallet.entity.UserCrypto;
import com.Crypto.Wallet.Crypto.Wallet.externaltokenapi.Crypto;
import com.Crypto.Wallet.Crypto.Wallet.repository.UserRepo;

//@SpringBootTest(classes = UserRepoTest.class)
@DataJpaTest
class UserRepoTest {

	@Autowired
	private UserRepo userRepoTest;
	
/**	private TestEntityManager testEntityManager;
	
	private User user;
	private UserCrypto userCrypto1;
	private UserCrypto userCrypto2;
	private IdentityVerification identityVerification;
	private Crypto crypto;
	private Crypto crypto2;**/
	
	/**@BeforeEach
	public void setUp() {
		user = new User("hank@gmail.com","12345");
		userCrypto1 = new UserCrypto(23.55);
		userCrypto2 = new UserCrypto(26.55);
		identityVerification = new IdentityVerification("NIN");
		crypto = new Crypto();X
		crypto2 = new Crypto();
	}**/
	@Test
	void testFindByEmail() {
		//given
		String email = "agu@gmail.com";
		User exist = new User();
		exist.setId(1);
		//when
		exist = userRepoTest.findByEmail(email);
		
		//then
		
		assertThat(exist.getEmail()).isEqualTo(email);
	}
	/**@Test
	public void saveUser() {
		User userSaved = this.userRepoTest.save(user);
		assertThat(userSaved.getEmail()).isEqualTo("hank@gmail.com");
	}**/

}
