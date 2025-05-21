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
public class Utente { //con @Entity il framework sa che a Recipe bisogna associare una tabella nel database
	//@GeneratedValue(strategy = GenerationType.AUTO)

	@Id	//chiave primaria della tabella
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Utente_seq")
	@SequenceGenerator(name = "Utente_seq", sequenceName = "Utente_seq", allocationSize = 1)	//incremento gli id di 1
	private Long id;

	@NotNull
	@NotBlank
	private String nome;

	@NotNull
	@NotBlank
	private String cognome;
		
	@ManyToMany(mappedBy = "utenti")
	private List<Recipe> ricetteFatte;
	
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