package com.hendel.circuitbreaker.domain.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class CircuitBreakerEndpoints {
    private final CircuitBreakerService circuitBreakerService;

    @GetMapping("/v1/{query}")
    public String callExternalService(@PathVariable String query) {
        return this.circuitBreakerService.callExternalService(query);
    }

    @GetMapping("/v2/{query}")
    public String callAnotherService(@PathVariable String query) {
        return this.circuitBreakerService.callAnotherExternalService(query);
    }
}
