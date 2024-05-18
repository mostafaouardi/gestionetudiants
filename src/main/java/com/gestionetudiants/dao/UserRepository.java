package com.gestionetudiants.dao;

import com.gestionetudiants.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<AppUser, Long>{
	public AppUser findByUsername(String username);
	
}
