package it.uniroma3.MyRecipe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity	
public class Credentials {

	public static final String DEFAULT_ROLE = "DEFAULT";
	public static final String ADMIN_ROLE = "ADMIN";
	
	@Id	//chiave primaria della tabella
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credentials_seq")
	@SequenceGenerator(name = "credentials_seq", sequenceName = "credentials_seq", allocationSize = 1)	//incremento gli id di 1
	private Long id;

	@NotNull
	@NotBlank
	private String username;

	@NotNull
	@NotBlank
	private String password;
	
	private String ruolo;
	
	@OneToOne
	private Utente utente;
	
	@Transient
	private String confirmPassword;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	public String getRuolo() {
		return ruolo;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	@Override
	public boolean equals(Object obj) {
		Recipe r = (Recipe) obj;
		return this.id == r.getId();
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}