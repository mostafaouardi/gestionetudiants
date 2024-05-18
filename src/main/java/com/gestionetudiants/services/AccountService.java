package com.gestionetudiants.services;


import java.util.List;

import com.gestionetudiants.entities.AppRole;
import com.gestionetudiants.entities.AppUser;
import org.springframework.stereotype.Service;


@Service
public interface AccountService {
	public AppUser saveUser(AppUser user);
	public AppUser saveUser(AppUser user,String action);
	public AppRole saveRole(AppRole role);
	public void addRoleToUser(String username, String rolename);
	public AppUser findUserByUsername(String username);
	public AppUser findUserByID(Long id);
	public List<AppUser> findAll();
	public void removeUser(Long id);
}
