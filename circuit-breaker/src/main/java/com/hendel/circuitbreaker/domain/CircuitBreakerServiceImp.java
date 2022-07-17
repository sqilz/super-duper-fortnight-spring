package com.hendel.circuitbreaker.domain;

import com.hendel.circuitbreaker.domain.api.CircuitBreakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
class CircuitBreakerServiceImp implements CircuitBreakerService {

    private final RestTemplate restTemplate = new RestTemplate();
    //private final SpringRetryCircuitBreakerFactory factory;
    //private final RetryTemplate factory;

    @Override
    @Retryable(RestClientException.class)
    public String retrieveInfo(String something) throws RestClientException {
        log.info("retrying service");
        if(something.equalsIgnoreCase("fail"))
        {
            throw new RestClientException(something);
        }
        return "not failed";
    }

}
