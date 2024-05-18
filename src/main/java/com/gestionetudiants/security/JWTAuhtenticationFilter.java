package com.gestionetudiants.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestionetudiants.entities.AppUser;
import com.gestionetudiants.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JWTAuhtenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	
	private AccountService accountService;
	
	private AuthenticationManager authenticationManager;
	
	//ma méthode a moir d'ajouter compte active ou pas!!!
	
	
	
	public JWTAuhtenticationFilter(AuthenticationManager authenticationManager,ApplicationContext ctx) {
		super();
		this.authenticationManager = authenticationManager;
		this.accountService=ctx.getBean(AccountService.class);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		AppUser appUser=null;
		try {
			appUser=new ObjectMapper().readValue(request.getInputStream(),AppUser.class);
		} catch (Exception e) {
		throw new RuntimeException();
		}
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(),appUser.getPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
		Authentication authResult) throws IOException, ServletException {
		User springUser=(User) authResult.getPrincipal();
		
		//jai cherche ici si le compte est actif ou pas encore
		AppUser userForClaim = accountService.findUserByUsername(springUser.getUsername());
		boolean activeB =userForClaim.getActive();
		Long id = userForClaim.getId();
		String jwt=Jwts.builder()
				.setSubject(springUser.getUsername())		
				.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.TIME_EXP))
				.signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
				.claim("roles", springUser.getAuthorities())
				//pour tester un user active ou pas '' jai ajouté un claim
				.claim("active", activeB)
				.claim("identif", id)
				.compact();
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwt);				
//		super.successfulAuthentication(request, response, chain, authResult);
	}
}
