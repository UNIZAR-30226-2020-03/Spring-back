package com.proyecto.upbeat.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Usuario")
public class UpbeatUserModel extends UpbeatClientModel{
	
	@NotNull
    @NotEmpty
	private String nom_user;
	
	@NotNull
    @NotEmpty
	private String matchingPassword;

	public UpbeatUserModel() {
		super();
	}

	public String getNom_user() {
		return nom_user;
	}

	public void setNom_user(String nom_user) {
		this.nom_user = nom_user;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
	
	
}
