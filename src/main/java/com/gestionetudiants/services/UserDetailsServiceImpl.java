package com.gestionetudiants.services;
import java.util.ArrayList;
import java.util.Collection;

import com.gestionetudiants.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AccountService accountservice;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user= accountservice.findUserByUsername(username);
		if (user==null) throw new UsernameNotFoundException(username);
		
		
		Collection<GrantedAuthority> authorities=new ArrayList<>();
		user.getRoles().forEach(r ->{
			authorities.add(new SimpleGrantedAuthority(r.getRolename()));
		});;
		return new User(user.getUsername(),user.getPassword(),authorities);
	}

}
