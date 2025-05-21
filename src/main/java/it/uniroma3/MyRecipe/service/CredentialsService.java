package it.uniroma3.MyRecipe.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.uniroma3.MyRecipe.model.Credentials;
import it.uniroma3.MyRecipe.repository.CredentialsRepository;
import jakarta.transaction.Transactional;

@Service
public class CredentialsService {

	@Autowired
	protected CredentialsRepository credenzialiRepository;
	
	@Autowired
	protected PasswordEncoder passwordEncoder;


	@Transactional
	public Credentials getCredenziali(Long id) {
		Optional<Credentials> result = this.credenzialiRepository.findById(id);
		return result.orElse(null);
	}

	@Transactional
	public Credentials getCredenziali(String username) {
		Optional<Credentials> result = this.credenzialiRepository.findByUsername(username);
		return result.orElse(null);
	}

	@Transactional
	public Credentials saveCredenziali(Credentials credenziali) {
		credenziali.setRuolo(Credentials.DEFAULT_ROLE);
		credenziali.setPassword(this.passwordEncoder.encode(credenziali.getPassword()));
		return this.credenzialiRepository.save(credenziali);
	}

}
