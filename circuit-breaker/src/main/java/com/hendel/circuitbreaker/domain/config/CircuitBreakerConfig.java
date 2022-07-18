package com.hendel.circuitbreaker.domain.config;

import com.hendel.circuitbreaker.domain.CustomRetryListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.policy.TimeoutRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.retry.support.RetryTemplateBuilder;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableRetry
public class CircuitBreakerConfig {

    @Bean
    public RetryTemplate retryTemplate() {
        //RetryTemplate retryTemplate = new RetryTemplate();

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(2);


        //retryTemplate.setRetryPolicy(simpleRetryPolicy);
        //retryTemplate.registerListener(new CustomRetryListener());


        return new RetryTemplateBuilder()
                .customPolicy(simpleRetryPolicy)
                .withListener(new CustomRetryListener())
                .customBackoff(new ExponentialBackOffPolicy())
                .build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
