package com.Crypto.Wallet.Crypto.Wallet.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;




@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig {
	@Autowired 
	private JwtTokenFilter jwtTokenFilter;
	
	/**
	 * This method is use to fetch a user and its details from database
	 * @return
	 */
	@Bean
    protected UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
	}
	 
	/**
	 * This method is use to encrypt user's password during sign up
	 * @return
	 */
	 @Bean
	    PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
	 /**
	  * This method is use to authenticate users
	  * @param authConfig
	  * @return
	  * @throws Exception
	  */
	 @Bean
	     AuthenticationManager authenticationManager(
	            AuthenticationConfiguration authConfig) throws Exception {
	        return authConfig.getAuthenticationManager();
	    }
	 
	 /**
	  * This method is use to authorize users
	  * @param http
	  * @return
	  * @throws Exception
	  */
	
	@Bean
	 SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//http.authorizeRequests().anyRequest().permitAll();
		http.authorizeRequests()
       // .antMatchers("/auth/login").permitAll()
        .antMatchers("/getTokens").permitAll()
       // .antMatchers("/signup").permitAll()
        .antMatchers("/signupPage").permitAll()
        .antMatchers("/forgotPasswordPage").permitAll()
        .antMatchers("/pricePage").permitAll()
        .antMatchers("/appPage").permitAll()
        .antMatchers("/aboutPage").permitAll()
        .antMatchers("/teamPage").permitAll()
        .antMatchers("/blogPage").permitAll()
        .antMatchers("/careerPage").permitAll()
        .antMatchers("/helpDeskPage").permitAll()
        .antMatchers("/contactPage").permitAll()
        .antMatchers("/privacyPage").permitAll()
        .antMatchers("/faqPage").permitAll()
        .antMatchers("/**/*.*").permitAll()
        .antMatchers("/loginPage").permitAll()
        .antMatchers("/homePage").permitAll()
        .and()
        .formLogin()
        .loginPage("/loginPage")
        .usernameParameter("email")
        .loginProcessingUrl("/auth/login")
        .defaultSuccessUrl("/dashboard")
        .permitAll()
	    .and()
	    .logout()
	    .logoutUrl("/logout")
	    .logoutSuccessUrl("/loginPage");
		
		
		
        //.antMatchers("/recover-password").permitAll()
        //.anyRequest().authenticated();
		http.exceptionHandling()
        .authenticationEntryPoint(
            (request, response, ex) -> {
                response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    ex.getMessage()
                );
            }
    );
		

http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
		}

}
