package ru.ilshat.firstSecurityApp.FirstSecurityApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.ilshat.firstSecurityApp.FirstSecurityApp.services.PersonDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration{
	
	private PersonDetailsService personDetailsService;

	@Autowired
	public SecurityConfig(PersonDetailsService personDetailsService) {
		this.personDetailsService = personDetailsService;
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf(csrf -> csrf
				.disable())
		.authorizeHttpRequests(authorize -> authorize
	            .requestMatchers("/hello").authenticated()
	            .anyRequest().permitAll())
        .formLogin(formLogin -> formLogin
	            .loginPage("/auth/login")
	            .loginProcessingUrl("/process_login")
	            .defaultSuccessUrl("/hello", true)
	            .failureUrl("/auth/login?error"));
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(personDetailsService);
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
