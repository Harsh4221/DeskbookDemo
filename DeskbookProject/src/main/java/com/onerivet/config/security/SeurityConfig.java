package com.onerivet.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SeurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.authorizeHttpRequests()
			.requestMatchers("/api/employees", "/api/employees/**").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	
	/*
	 * @Bean AuthenticationProvider authenticationProvider() {
	 * DaoAuthenticationProvider daoAuthenticationProvider = new
	 * DaoAuthenticationProvider();
	 * 
	 * daoAuthenticationProvider.setUserDetailsService(this.userDetailsService());
	 * daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
	 * 
	 * return daoAuthenticationProvider; }
	 * 
	 * @Bean UserDetailsService userDetailsService() { return null; }
	 */
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
}
