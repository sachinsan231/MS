/**
 * 
 */
package com.example.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author User
 *
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		http.authorizeExchange( exchanges -> exchanges.pathMatchers("/api/accounts/**").authenticated()
				.pathMatchers("/api/loans/**").authenticated()
				.pathMatchers("/api/cards/**").permitAll())
				.oauth2ResourceServer().jwt();
		http.csrf().disable();
		return http.build();
	}

}
