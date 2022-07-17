package com.hendel.circuitbreaker.domain.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.circuitbreaker.springretry.SpringRetryCircuitBreaker;
import org.springframework.cloud.circuitbreaker.springretry.SpringRetryCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.retry.annotation.Recover;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class CircuitBreakerEndpoints {
    private final CircuitBreakerService circuitBreakerService;
    private final SpringRetryCircuitBreakerFactory cb;

    @GetMapping("/{something}")
    //@CircuitBreaker(maxAttempts=3,openTimeout = 3000L, resetTimeout = 2_000L)
    public String retrieveSomeInfo(@PathVariable String something) {
        log.info("retrying cb");
        CircuitBreaker s = cb.create("s");
        return s.run(() -> this.circuitBreakerService.retrieveInfo(something));
    }

    @Recover
    public String fallback() {
        log.warn("recovering circuit breaker");
        return "Something is no yes";
    }
}
