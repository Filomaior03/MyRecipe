package it.uniroma3.MyRecipe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;

@Entity
public class Provenienza {
	@Id	//chiave primaria della tabella
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "provenienza_seq")
	@SequenceGenerator(name = "provenienza_seq", sequenceName = "provenienza_seq", allocationSize = 1)	//incremento gli id di 1
	private Long id;

	@NotNull
	private String nome;

	@NotNull
	private String continente;
	
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
	
	public String getContinente() {
		return continente;
	}
	
	public void setContinente(String continente) {
		this.continente = continente;
	}

	@Override
	public boolean equals(Object obj) {
		Provenienza p = (Provenienza) obj;
		return this.id == p.getId();
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
}
