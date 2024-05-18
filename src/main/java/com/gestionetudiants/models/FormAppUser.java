package com.gestionetudiants.models;

import lombok.Data;
@Data
public class FormAppUser {
	private String username;
	private String password;
	private String repassword;
	private String role;
}
