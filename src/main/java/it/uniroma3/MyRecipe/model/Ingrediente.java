package it.uniroma3.MyRecipe.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;

@Entity	
public class Ingrediente {
  
  @Id	//chiave primaria della tabella
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @NotNull
  private String nome;
    
  @ManyToMany
  private List<Ricetta> ricette;
  
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getNome() {
    return nome;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setRicette(List<Ricetta> ricette) {
    this.ricette = ricette;
  }
  
  public List<Ricetta> getRicette() {
    return ricette;
  }
  
  @Override
  public boolean equals(Object obj) {
    Ingrediente i = (Ingrediente) obj;
    return this.id == i.getId();
  }
  
  @Override
  public int hashCode() {
    return this.id.hashCode();
  }
}