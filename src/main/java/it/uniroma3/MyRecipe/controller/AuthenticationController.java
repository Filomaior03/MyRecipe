package it.uniroma3.MyRecipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.uniroma3.MyRecipe.model.Credentials;
import it.uniroma3.MyRecipe.model.Utente;
import it.uniroma3.MyRecipe.service.CredentialsService;
import it.uniroma3.MyRecipe.service.UtenteService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthenticationController {

	@Autowired
	private UtenteService utenteService;

	@Autowired
	private CredentialsService credentialsService;

	@GetMapping(value = "/") 
	public String index(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
			return "index.html";
		}
		else {		
			UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
			if (credentials.getRuolo().equals(Credentials.ADMIN_ROLE)) {
				return "admin/indexAdmin.html";
			}
		}
		return "index.html";
	}

	@GetMapping(value = "/login")
	public String showLoginForm() {
		return "formLogin";
	}

	@GetMapping(value = "/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("utente", new Utente());
		model.addAttribute("credentials", new Credentials());
		return "formRegisterUtente";
	}	
}