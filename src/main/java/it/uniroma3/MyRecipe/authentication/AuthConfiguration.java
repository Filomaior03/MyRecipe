package it.uniroma3.MyRecipe.authentication;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
		.usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?")
		.authoritiesByUsernameQuery("SELECT username, role from credentials WHERE username=?");
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
				.requestMatchers("/", "/login", "/register", "/css/**", "/images/**", "/favicon.ico").permitAll()
				//tutte le altre necessitano invece di autenticarsi
				.anyRequest().authenticated()
				)
		//Qui si gestisce implicitamente la richiesta di POST per le informazioni del login
		.formLogin(form -> form
				.loginPage("/login")
				.permitAll()
				//pagina da restituire se il login va a buon fine
				.defaultSuccessUrl("/dashboard", true)
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
