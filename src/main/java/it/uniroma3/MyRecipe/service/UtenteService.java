package it.uniroma3.MyRecipe.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.MyRecipe.model.Utente;
import it.uniroma3.MyRecipe.repository.UtenteRepository;
import jakarta.transaction.Transactional;

@Service
public class UtenteService {

	@Autowired protected UtenteRepository utenteRepository;

	//metodo che in base all'id cerca e trova l'utente nel database
	@Transactional
	public Utente getUtente(Long id) {
		Optional<Utente> result = this.utenteRepository.findById(id);
		return result.orElse(null);
	}

	//metodo che salva l'utente nel database
	@Transactional
	public Utente saveUtente(Utente utente) {
		return this.utenteRepository.save(utente);
	}
	
	//metodo che ritorna la lista degli utenti presenti nel database
	@Transactional
	public List<Utente> getAllUtenti() {
		List<Utente> result = new ArrayList<>();
		Iterable<Utente> iterable = this.utenteRepository.findAll();
		for (Utente user : iterable)
			result.add(user);
		return result;
	}
}
