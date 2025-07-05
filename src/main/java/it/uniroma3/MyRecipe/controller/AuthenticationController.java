package it.uniroma3.MyRecipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import it.uniroma3.MyRecipe.model.Credenziali;
import it.uniroma3.MyRecipe.model.Utente;
import it.uniroma3.MyRecipe.service.CredenzialiService;
import it.uniroma3.MyRecipe.service.UtenteService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller			
public class AuthenticationController {

	@Autowired
	private CredenzialiService credentialsService;
	
	@Autowired
	private UtenteService utenteService;

	//restituisce la home - PUBBLICA
	@GetMapping(value = "/") 
	public String showHome() {
		return "home";
	}

	//restituisce la pagina per il login - PUBBLICA
	@GetMapping("/login")
	public String showLoginForm() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
	        //utente già loggato, redirect alla dashboard
	        return "redirect:/dashboard";
	    }
	    //utente non loggato, torna alla pagina per fare il login
	    return "formLogin";
	}

	//restituisce la dashboard a seconda di chi accede - NECESSARIA AUTENTICAZIONE
	@GetMapping(value = "/dashboard")
	public String showDashboard() {
		//recupero le credenziali dell'utente che ha fatto l'accesso
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		//se è un admin
		if (credentialsService.isAdmin(userDetails.getUsername())) {
			return "admin/dashboard.html";	//dashboard dell'admin
		}
		return "dashboard";	//dashboard per gli utenti normali
	}

	//restituisce la pagina per la registrazione - PUBBLICA
	@GetMapping(value = "/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("utente", new Utente());
		model.addAttribute("credentials", new Credenziali());
		return "formRegister";
	}
	
	@PostMapping(value = "/register")
	public String saveCredentials(@Valid @ModelAttribute("utente") Utente utente, BindingResult brU, 
			@Valid @ModelAttribute("credentials") Credenziali credentials, BindingResult brC, Model model) {
		if(brU.hasErrors() || brC.hasErrors())
			return "formRegister";
		
		//la conferma della password non va a buon fine
		if(!credentials.getPassword().equals(credentials.getConfirmPassword())) {
			//messaggio di errore
			brC.rejectValue("confirmPassword", "error.confirmPassword", "Le password non coincidono");
			return "formRegister";
		}
		
		//nessun errore nella registrazione, salvo l'utente e le sue credenziali e faccio fare all'utente il login
		else{
			this.utenteService.saveUtente(utente);
			this.credentialsService.saveCredentials(credentials);
			model.addAttribute("utente", utente);
		}
		return "redirect:/login";
	}
}