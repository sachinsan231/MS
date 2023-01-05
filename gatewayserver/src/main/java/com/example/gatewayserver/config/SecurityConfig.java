/**
 * 
 */
package com.example.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

/**
 * @author User
 *
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		http.authorizeExchange( exchanges -> exchanges.pathMatchers("/api/accounts/**").hasAnyRole("ACCOUNTS")
				.pathMatchers("/api/loans/**").authenticated()
				.pathMatchers("/api/cards/**").permitAll())
				.oauth2ResourceServer().jwt().jwtAuthenticationConverter(grantedAuthoritiesExtractor());
		http.csrf().disable();
		return http.build();
	}
	
	Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor(){
		JwtAuthenticationConverter authenticationConverter= new JwtAuthenticationConverter();
		authenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
		return new ReactiveJwtAuthenticationConverterAdapter(authenticationConverter);
	}

}
