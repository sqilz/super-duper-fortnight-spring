package com.hendel.circuitbreaker.domain.api;

import org.springframework.web.client.RestClientException;

public interface CircuitBreakerService {
    String retrieveInfo(String something) throws RestClientException;
}
