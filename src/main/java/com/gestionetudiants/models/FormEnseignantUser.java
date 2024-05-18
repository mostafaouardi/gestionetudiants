package com.gestionetudiants.models;

import lombok.Data;

@Data
public class FormEnseignantUser {
	private String nom;
	private String prenom;
	private String ville;
	private String telephone;
	private String username;
	private String password;
	private String repassword;
}
