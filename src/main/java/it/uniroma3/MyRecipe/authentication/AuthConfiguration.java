package it.uniroma3.MyRecipe.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static it.uniroma3.MyRecipe.model.Credenziali.ADMIN_ROLE;
import static it.uniroma3.MyRecipe.model.Credenziali.DEFAULT_ROLE;

/* CLASSE DI CONFIGURAZIONE PER LA SICUREZZA */

@Configuration
@EnableWebSecurity
public class AuthConfiguration {

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credenziali WHERE username=?")
		.authoritiesByUsernameQuery("SELECT username, ruolo from credenziali WHERE username=?");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf(csrf -> csrf.disable())
		
		.authorizeHttpRequests(auth -> auth
				//questi path sono raggiungibili da tutti senza autenticarsi 
				.requestMatchers(HttpMethod.GET, "/", "/index", "/register", "/css/style.css", "/images/**", "/logo/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/register").permitAll()
				.requestMatchers("/ricetta/**").hasAnyAuthority(ADMIN_ROLE, DEFAULT_ROLE)
				//tutte le altre necessitano invece di autenticarsi
				.anyRequest().authenticated()
				)
		//Qui si gestisce implicitamente la richiesta di POST per le informazioni del login
		.formLogin(form -> form
				.loginPage("/login")
				.permitAll()
				//pagina da restituire se il login va a buon fine
				.defaultSuccessUrl("/index", true)
				.failureUrl("/login?error=true")
				)
		.logout(logout -> logout
				.logoutUrl("/logout")
				//pagina da restituire se il logout va a buon fine
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.clearAuthentication(true)
				);
		
		return http.build();  // Restituisce la configurazione
	}
}
