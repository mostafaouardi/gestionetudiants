package com.gestionetudiants.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class AppRole implements Serializable{
	@Id @GeneratedValue
	private Long id;
	private String rolename;
	// je nai pas besoind une relation bidirectionn
	// je vai a chercher les users quiont un role x//
	public Long getId() {
		return id;
	}
	public AppRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AppRole(Long id, String rolename) {
		super();
		this.id = id;
		this.rolename = rolename;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}
