package it.uniroma3.MyRecipe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.requestMatchers("/**").permitAll()  // 🔓 permette tutto
                )
                .formLogin(login -> login.disable())                   // 🚫 disabilita il login automatico
                .csrf(csrf -> csrf.disable());                       // (opzionale) disabilita CSRF per test
		return http.build();
	}
}