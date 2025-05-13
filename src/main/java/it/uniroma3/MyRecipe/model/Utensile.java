package it.uniroma3.MyRecipe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;

@Entity
public class Utensile {
	@Id	//chiave primaria della tabella
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utensile_seq")
	@SequenceGenerator(name = "utensile_seq", sequenceName = "utensile_seq", allocationSize = 1)	//incremento gli id di 1
	private Long id;

	@NotNull
	private String nome;
	
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

	@Override
	public boolean equals(Object obj) {
		Utensile u = (Utensile) obj;
		return this.id == u.getId();
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
