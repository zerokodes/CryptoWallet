package com.Crypto.Wallet.Crypto.Wallet.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Crypto.Wallet.Crypto.Wallet.repository.UserRepo;





@Component
public class JwtTokenFilter extends OncePerRequestFilter{
	@Autowired
    private JwtTokenUtil jwtUtil;
	
	
	@Autowired
	private UserRepo userRepo;
	
    
	/**
	 * This method checks if a request has authorization bearer,
	 * if true then gets the access token and validates it,
	 * if validation is successful it calls the setAuthenticationContext 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if (!hasAuthorizationBearer(request)) {
            filterChain.doFilter(request, response);
            return;
        }
 
        String token = getAccessToken(request);
 
        if (!jwtUtil.validateAccessToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }
 
        setAuthenticationContext(token, request);
        filterChain.doFilter(request, response);
		
	}
	
	/**
	 * This method checks if a user has the Bearer word
	 * in the authorization header
	 * @param request
	 * @return
	 */
	private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
            return false;
        }
 
        return true;
    }
 
	/**
	 * This method gets the access token from
	 * the header of the user sending a request
	 * @param request
	 * @return
	 */
    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        return token;
    }
 
    private void setAuthenticationContext(String token, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(token);
 
        UsernamePasswordAuthenticationToken
            authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);
 
        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));
 
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
 
    /**
     * This method gets the user details from the database
     * and extract the user id and email which is used for the 
     * t0ken generation
     * @param token
     * @return
     */
    private UserDetails getUserDetails(String token) {
     
        String[] jwtSubject = jwtUtil.getSubject(token).split(",");
        CustomUserDetails userDetails = new CustomUserDetails(userRepo.findByEmail(jwtSubject[1]));
 
        userDetails.getUser().setId(Integer.parseInt(jwtSubject[0]));
        userDetails.getUser().setEmail(jwtSubject[1]);
 
        return userDetails;
    	
    }
	

}
