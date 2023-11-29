package ru.ilshat.firstSecurityApp.FirstSecurityApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ru.ilshat.firstSecurityApp.FirstSecurityApp.services.PersonDetailsService;

@Configuration
public class SecurityConfig {
	
	private final PersonDetailsService personDetailsService;
	
	public SecurityConfig(PersonDetailsService personDetailsService) {
		this.personDetailsService = personDetailsService;
	}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(authorize -> authorize
	            .requestMatchers("/hello").authenticated()
	            .anyRequest().permitAll())
        .formLogin(formLogin -> formLogin
	            .loginPage("/auth/login")
	            .loginProcessingUrl("/process_login")
	            .defaultSuccessUrl("/hello", true)
	            .failureUrl("/auth/login?error"))
		.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/auth/login"));
		
		return http.build();
	}

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(personDetailsService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	   
	      return authProvider;
	  }

    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
