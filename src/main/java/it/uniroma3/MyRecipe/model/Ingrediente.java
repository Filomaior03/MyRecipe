package it.uniroma3.MyRecipe.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity	
public class Ingrediente {
  
  @Id	//chiave primaria della tabella
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @NotNull
  @NotBlank
  private String nome;
    
  @ManyToMany(mappedBy = "ingredienti")
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
    return this.ricette;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;             // stesso riferimento
    if (obj == null || getClass() != obj.getClass()) return false; // null o classe diversa
    
    Ingrediente r = (Ingrediente) obj;
    
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