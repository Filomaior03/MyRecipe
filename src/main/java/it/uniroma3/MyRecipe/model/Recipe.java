package it.uniroma3.MyRecipe.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity	
public class Recipe { //con @Entity il framework sa che a Recipe bisogna associare una tabella nel database
	//@GeneratedValue(strategy = GenerationType.AUTO)

	@Id	//chiave primaria della tabella
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_seq")
	@SequenceGenerator(name = "recipe_seq", sequenceName = "recipe_seq", allocationSize = 1)	//incremento gli id di 1
	private Long id;

	@NotNull
	@NotBlank
	private String nome;

	@NotNull
	@NotBlank
	private String tempoDiPreparazione;
	
	private String provenienza;

	@ManyToMany
	private List<Ingredient> ingredienti;
	
	@ManyToMany
	private List<Utensile> utensili;
	
	@ManyToMany
	private List<Utente> utenti;
	
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
	
	public List<Ingredient> getIngredienti() {
		return ingredienti;
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