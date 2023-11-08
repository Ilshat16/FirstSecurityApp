package ru.ilshat.firstSecurityApp.FirstSecurityApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ru.ilshat.firstSecurityApp.FirstSecurityApp.services.PersonDetailsService;

@Configuration
public class SecurityConfig {
	
	private final PersonDetailsService personDetailsService;
	
	@Autowired
	public SecurityConfig(PersonDetailsService personDetailsService) {
		this.personDetailsService = personDetailsService;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
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
	 public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(personDetailsService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	   
	      return authProvider;
	  }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
