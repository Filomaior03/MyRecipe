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
	protected CredentialsRepository credentialsRepository;

	@Autowired
	protected PasswordEncoder passwordEncoder;

	//cerca le credenziali nel DB in base all'id
	@Transactional
	public Credentials getCredentialsById(Long id) {
		Optional<Credentials> result = this.credentialsRepository.findById(id);
		return result.orElse(null);
	}

	//cerca le credenziali nel DB in base allo username
	@Transactional
	public Credentials getCredentialsByUsername(String username) {
		Optional<Credentials> result = this.credentialsRepository.findByUsername(username);
		return result.orElse(null);
	}

	//salva le credenziali nel DB, impostando il ruolo a DEFAULT e criptando la password
	@Transactional
	public Credentials saveCredentials(Credentials credentials) {
		credentials.setRuolo(Credentials.DEFAULT_ROLE);
		credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
		return this.credentialsRepository.save(credentials);
	}

	public boolean validateCredentials(String username, String rawPassword) {
		Credentials result = getCredentialsByUsername(username);
		
		if(result == null)
			return false;
		return (passwordEncoder.matches(rawPassword, result.getPassword()));
	}
	
	public boolean isAdmin(String username) {
		Credentials result = getCredentialsByUsername(username);
		
		if(result == null)
			return false;
		return ("admin".equalsIgnoreCase(username));
	}
}



