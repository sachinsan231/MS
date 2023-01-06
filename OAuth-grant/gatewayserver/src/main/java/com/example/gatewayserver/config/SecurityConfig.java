/**
 * 
 */
package com.example.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(exchanges -> exchanges.pathMatchers("/api/accounts/**").authenticated()
                        .pathMatchers("/api/cards/**").authenticated()
                        .pathMatchers("/api/loans/**").permitAll())
                .oauth2Login(Customizer.withDefaults());
        http.csrf().disable();
        return http.build();
    }
	
}
