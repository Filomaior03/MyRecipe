package it.uniroma3.MyRecipe.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity	
public class Recipe { //con @Entity il framework sa che a Recipe bisogna associare una tabella nel database
	//@GeneratedValue(strategy = GenerationType.AUTO)

	@Id	//chiave primaria della tabella
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ricetta_seq")
	@SequenceGenerator(name = "ricetta_seq", sequenceName = "ricetta_seq", allocationSize = 1)	//incremento gli id di 1
	private Long id;

	@NotNull
	@NotBlank
	private String nome;

	@NotNull
	@NotBlank
	private String tempoDiPreparazione;

	@ManyToMany
	private List<Ingrediente> ingredienti;
	
	@ManyToMany
	private List<Utensile> utensili;
	
	@ManyToOne
	private Provenienza provenienza; 
	
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