package com.gestionetudiants.dao;

import com.gestionetudiants.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<AppRole, Long> {
	public AppRole findByRolename(String roleName);
}
