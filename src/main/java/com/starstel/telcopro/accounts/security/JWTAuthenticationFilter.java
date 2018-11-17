package com.starstel.telcopro.accounts.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starstel.telcopro.accounts.entities.AppUser;
import com.starstel.telcopro.accounts.services.AccountService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Systeme D'authenfication par Valeur Via JWT, A la Difference de, par Reference via les Sessions
 * @author Sosthene Nouebissi
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
	private AuthenticationManager authenticationManager;
	@Autowired
	private AccountService accountService;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager)
	{
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException
	{
		AppUser user = null;
		
		try 
		{
			user = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
		} 
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
		
		System.out.println("******************");
		System.out.println("Username : " + user.getUsername());
		System.out.println("Password : " + user.getPassword());
		
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
			user.getUsername(), user.getPassword()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException 
	{
		User user = (User) authResult.getPrincipal();
		
		String jwtToken = Jwts.builder()
				.setSubject(user.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
				.claim("roles", user.getAuthorities())
				.compact();
		
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + jwtToken);
	}
}
