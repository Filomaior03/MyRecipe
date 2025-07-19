package it.uniroma3.MyRecipe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity	
public class Credenziali {

	public static final String DEFAULT_ROLE = "UTENTE";
	public static final String ADMIN_ROLE = "ADMIN";

	@Id	//chiave primaria della tabella
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@NotBlank
	private String username;

	@NotNull
	@NotBlank
	private String password;

	private String ruolo;

	@OneToOne(mappedBy = "credenziali")
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

	public Utente getUtente() {
		return this.utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;             // stesso riferimento
    if (obj == null || getClass() != obj.getClass()) return false; // null o classe diversa
    
    Credenziali r = (Credenziali) obj;
    
    if (this.id == null) {
      return r.getId() == null;
    } else {
      return this.id.equals(r.getId());
    }
  }
  
  @Override
  public int hashCode() {
    return (id == null) ? 0 : id.hashCode();
  }
}