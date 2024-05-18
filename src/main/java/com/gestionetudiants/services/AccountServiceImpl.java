package com.gestionetudiants.services;



import com.gestionetudiants.dao.RoleRepository;
import com.gestionetudiants.dao.UserRepository;
import com.gestionetudiants.entities.AppRole;
import com.gestionetudiants.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private BCryptPasswordEncoder bCrypt;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private Environment env;
	
	@Override
	public AppUser saveUser(AppUser user) {
		String cryptedPass = bCrypt.encode(user.getPassword());
		user.setPassword(cryptedPass);
		return userRepository.save(user); 
	}

	@Override
	public AppUser saveUser(AppUser user,String action) {
		return userRepository.save(user); 
	}
	
	
	@Override
	public AppRole saveRole(AppRole role) {
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String rolename) {
		AppUser user = userRepository.findByUsername(username);
		if(user.getRoles() == null) {
		user.setRoles(new ArrayList<>());
		}
		userRepository.save(user);
		userRepository.findByUsername(username).getRoles()
		.add(roleRepository.findByRolename(rolename));
		
	}

	@Override
	public AppUser findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public AppUser findUserByID(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<AppUser> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void removeUser(Long id) {
	 userRepository.delete(id);
	}

	
}
