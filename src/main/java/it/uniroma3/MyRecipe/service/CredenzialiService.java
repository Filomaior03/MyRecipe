package it.uniroma3.MyRecipe.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.uniroma3.MyRecipe.model.Credenziali;
import it.uniroma3.MyRecipe.repository.CredenzialiRepository;
import jakarta.transaction.Transactional;

@Service
public class CredenzialiService {

	@Autowired
	protected CredenzialiRepository credenzialiRepository;

	@Autowired
	protected PasswordEncoder passwordEncoder;

	//cerca le credenziali nel DB in base all'id
	@Transactional
	public Credenziali getCredenzialiById(Long id) {
		Optional<Credenziali> result = this.credenzialiRepository.findById(id);
		return result.orElse(null);
	}

	//cerca le credenziali nel DB in base allo username
	@Transactional
	public Credenziali getCredenzialiByUsername(String username) {
		Optional<Credenziali> result = this.credenzialiRepository.findByUsername(username);
		return result.orElse(null);
	}

	//salva le credenziali nel DB, impostando il ruolo a DEFAULT e criptando la password
	@Transactional
	public Credenziali saveCredenziali(Credenziali credenziali) {
		credenziali.setRuolo(Credenziali.DEFAULT_ROLE);
		credenziali.setPassword(this.passwordEncoder.encode(credenziali.getPassword()));
		return this.credenzialiRepository.save(credenziali);
	}

	public boolean validateCredenziali(String username, String rawPassword) {
		Credenziali result = getCredenzialiByUsername(username);
		
		if(result == null)
			return false;
		return (passwordEncoder.matches(rawPassword, result.getPassword()));
	}
	
	public boolean isAdmin(String username) {
		Credenziali result = getCredenzialiByUsername(username);
		
		if(result == null)
			return false;
		return ("admin".equalsIgnoreCase(username));
	}
}



