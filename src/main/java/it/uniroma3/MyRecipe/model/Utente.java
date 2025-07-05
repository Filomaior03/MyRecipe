package it.uniroma3.MyRecipe.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity	
public class Utente { //con @Entity il framework sa che a Recipe bisogna associare una tabella nel database
  
  @Id	//chiave primaria della tabella
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @NotNull
  @NotBlank
  private String nome;
  
  @NotNull
  @NotBlank
  private String cognome;
  
  @NotNull
  @NotBlank
  private String email;
  
  @OneToOne
  private Credenziali credenziali;
  
  @OneToMany
  private List<Ricetta> ricette;
  
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public String getNome() {
    return nome;
  }
  
  public void setCognome(String cognome) {
    this.cognome = cognome;
  }
  
  public String getCognome() {
    return cognome;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setCredentials(Credenziali credenziali) {
    this.credenziali = credenziali;
  }
  
  public Credenziali getCredentials() {
    return credenziali;
  }
  
  @Override
  public boolean equals(Object obj) {
    Ricetta r = (Ricetta) obj;
    return this.id == r.getId();
  }
  
  @Override
  public int hashCode() {
    return this.id.hashCode();
  }
}