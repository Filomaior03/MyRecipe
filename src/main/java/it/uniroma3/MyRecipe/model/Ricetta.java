package it.uniroma3.MyRecipe.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity	
public class Ricetta { //con @Entity il framework sa che a Ricetta bisogna associare una tabella nel database
  
  @Id	//chiave primaria della tabella
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @NotNull
  @NotBlank
  private String nome;
  
  private String tempoDiPreparazione;
  
  @NotBlank
  private String provenienza;
  
  @NotBlank
  private String categoria;
  
  private String img; 
  
  @Column(length = 2000)
  private String procedimento;
  
  @ManyToMany(mappedBy = "ricette")
  private List<Ingrediente> ingredienti;
  
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
  
  public String getTempoDiPreparazione() {
    return tempoDiPreparazione;
  }
  
  public void setTempoDiPreparazione(String tempoDiPreparazione) {
    this.tempoDiPreparazione = tempoDiPreparazione;
  }
  
  public String getProvenienza() {
    return provenienza;
  }
  
  public void setProvenienza(String provenienza) {
    this.provenienza = provenienza;
  }
  
  public void setProcedimento(String procedimento) {
    this.procedimento = procedimento;
  }
  
  public String getProcedimento() {
    return procedimento;
  }
  
  public void setImg(String img) {
    this.img = img;
  }
  
  public String getImg() {
    return img;
  }
  
  public void setIngredienti(List<Ingrediente> ingredienti) {
    this.ingredienti = ingredienti;
  }
  
  public List<Ingrediente> getIngredienti() {
    return ingredienti;
  }
  
  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }
  
  public String getCategoria() {
    return categoria;
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