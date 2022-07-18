package com.hendel.circuitbreaker.domain.api;

import org.springframework.web.client.RestClientException;

public interface CircuitBreakerService {
    String callExternalService(String something) throws RestClientException;

    String callAnotherExternalService (String something) throws RestClientException;
}
