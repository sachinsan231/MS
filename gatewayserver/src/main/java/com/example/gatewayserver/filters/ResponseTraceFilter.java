package com.example.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import reactor.core.publisher.Mono;

@Configuration
public class ResponseTraceFilter {

	private static final Logger logger = LoggerFactory.getLogger(ResponseTraceFilter.class);

	@Autowired
	FilterUtility filterUtility;
	
	@Bean
	public GlobalFilter posGlobalFilter() {
		return (exhcange, chain) ->{
			return chain.filter(exhcange).then(Mono.fromRunnable(() -> {
				HttpHeaders headers = exhcange.getRequest().getHeaders();
				String correlationId = filterUtility.getCorrelationId(headers);
				logger.debug("Updated the correlation id to the outbound headers. {}", correlationId);
				exhcange.getResponse().getHeaders().add(FilterUtility.CORRELATION_ID, correlationId);
			}));
		};
	}
}
