package it.uniroma3.MyRecipe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;

@Entity	
public class Ingredient {
	//@GeneratedValue(strategy = GenerationType.AUTO)

	@Id	//chiave primaria della tabella
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_seq")
	@SequenceGenerator(name = "ingredient_seq", sequenceName = "ingredient_seq", allocationSize = 1)	//incremento gli id di 1
	private Long id;

	@NotNull
	private String nome;

	@NotNull
	private int quantità;
	
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

	public int getQuantità() {
		return this.quantità;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}

	@Override
	public boolean equals(Object obj) {
		Ingredient i = (Ingredient) obj;
		return this.id == i.getId();
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}