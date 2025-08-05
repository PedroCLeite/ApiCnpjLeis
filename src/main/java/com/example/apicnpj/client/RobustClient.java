package com.example.apicnpj.client;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.core.IntervalFunction;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;

import java.time.Duration;
import java.util.function.Supplier;

public class RobustClient {

    public static final int MAX_ATTEMPTS = 3;
    public static final long INITIAL_INTERVAL_MILLIS = 100L;
    public static final long SECONDS = 10L;

    private final Retry retry;
    private final CircuitBreaker circuitBreaker;

    public RobustClient(String serviceName) {
        retry = Retry
                .of(serviceName,
                        RetryConfig
                                .custom()
                                .maxAttempts(MAX_ATTEMPTS)
                                .intervalFunction(IntervalFunction.ofExponentialBackoff(INITIAL_INTERVAL_MILLIS))
                                .build());

        circuitBreaker = CircuitBreaker.of(serviceName,
                CircuitBreakerConfig
                        .custom()
                        .enableAutomaticTransitionFromOpenToHalfOpen()
                        .waitDurationInOpenState(Duration.ofSeconds(SECONDS))
                        .build());
    }

    protected <T> T robustCall(final Supplier<T> supplier) {
        Supplier<T> decoratedSupplier = CircuitBreaker.decorateSupplier(circuitBreaker, supplier);
        decoratedSupplier = Retry.decorateSupplier(retry, decoratedSupplier);

        return decoratedSupplier.get();
    }
}
