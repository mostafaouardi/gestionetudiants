package com.gestionetudiants.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
	
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Headers", "*");
		
//		response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-width,"
//				+ "Content-Type, Access-Control-Request-Method, X-CSRF-Token, Access-Control-Request-Headers, Authorization ");
		
		
		response.addHeader("Access-Control-Expose-Headers", "*");
		
//		response.addHeader("Access-Control-Expose-Headers", 
//				"Access-Control-Allow-Origin, Access-Control-Allow-Credentials, Authorization");
		
		if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		// si le client JS demande que les options on sarrete ici la pr le traitement
		}
		
		else {
			// intercepter la requette et creer un jwt
			String jwt=request.getHeader(SecurityConstants.HEADER_STRING);
			if(jwt==null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)) {
				filterChain.doFilter(request, response); return;
			}
			
			//Récupérer les claims et enlever le préfixe Bearer
			Claims claims = Jwts.parser()
					.setSigningKey(SecurityConstants.SECRET)
					.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX, ""))
					.getBody();
			
			// Réc le User
			String username=claims.getSubject();
			
			//Les roles en string
			ArrayList<Map<String,String>> roles=(ArrayList<Map<String,String>>) claims.get("roles");
			
			//Les convertir en Autorizations
			Collection<GrantedAuthority> authorities= new ArrayList<GrantedAuthority>();
			
			roles.forEach(r->{
				authorities.add(new SimpleGrantedAuthority(r.get("authority")));
			});
			
			// créee le userToken
			
			UsernamePasswordAuthenticationToken authenticatedUser=
					//pa besoin du password on valide ici ke le token s il est valide !!
					new UsernamePasswordAuthenticationToken(username,null,authorities);
				SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
					filterChain.doFilter(request, response);
	
		}
				
	}

}
