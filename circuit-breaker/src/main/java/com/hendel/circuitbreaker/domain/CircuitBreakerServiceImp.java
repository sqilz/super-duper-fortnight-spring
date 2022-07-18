package com.hendel.circuitbreaker.domain;

import com.hendel.circuitbreaker.domain.api.CircuitBreakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
class CircuitBreakerServiceImp implements CircuitBreakerService {

    private final RetryTemplate retryTemplate;
    private final RestTemplate restTemplate;


    @Override
    @Retryable(value = RestClientException.class,

            backoff = @Backoff(delay = 500L))
    public String callExternalService(String something) throws RestClientException {
        log.info("retrying in service method... 1");
        if (something.equalsIgnoreCase("fail")) {
            // -- unsuccessful call to other service REST Api
            throw new RestClientException(something);
        }
        return "success";
    }

    @Override
    public String callAnotherExternalService(String something) throws RestClientException {
        return retryTemplate.execute(retryContext -> {
            log.info("retrying in service method... 2");

            return restTemplate.getForObject("http://localhost:8080", String.class);

        }, new RecoveryCallback<String>() {
            @Override
            public String recover(RetryContext context) throws Exception {
                return null;
            }
        }, );
    }

    @Recover
    public String fallback() {
        log.warn("recovering circuit breaker");
        return "fallback method";
    }

    @Recover
    String fallbackMethod(RestClientException exception, String s) {

        log.warn("recovering circuit breaker2");
        return "second fallback";
    }
}
