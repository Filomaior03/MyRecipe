package it.uniroma3.MyRecipe.model;

import java.util.List;

import jakarta.persistence.CascadeType;
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
  
  @OneToOne(cascade = CascadeType.ALL)	//se salvo l'utente salvo anche le credenziali 
  private Credenziali credenziali;
  
  @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
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
  
  public List<Ricetta> getRicette() {
    return ricette;
  }
  
  public void setRicette(List<Ricetta> ricette) {
    this.ricette = ricette;
  }
  
  public Credenziali getCredentials() {
    return credenziali;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;             // stesso riferimento
    if (obj == null || getClass() != obj.getClass()) return false; // null o classe diversa
    
    Utente r = (Utente) obj;
    
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