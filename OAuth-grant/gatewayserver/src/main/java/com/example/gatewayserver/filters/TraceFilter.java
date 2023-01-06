package com.example.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Order(1)
@Component
public class TraceFilter implements GlobalFilter {

	private static final Logger logger = LoggerFactory.getLogger(TraceFilter.class);
	@Autowired
	private FilterUtility filterUtility;
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		HttpHeaders headers = exchange.getRequest().getHeaders();
		if(isCorrelationIdPresent(headers)) {
			logger.debug("API-correlation-id found in tracing filter: {}. ",
					filterUtility.getCorrelationId(headers));
		}else {
			String correlationID = generateCorrelationId();
			exchange = filterUtility.setCorrelationId(exchange, correlationID);
			logger.debug("API-correlation-id generated in tracing filter: {}.", correlationID);
		}
		return chain.filter(exchange);
	}
	
	
	private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
		if (filterUtility.getCorrelationId(requestHeaders) != null) {
			return true;
		} else {
			return false;
		}
	}

	private String generateCorrelationId() {
		return java.util.UUID.randomUUID().toString();
	}

}
