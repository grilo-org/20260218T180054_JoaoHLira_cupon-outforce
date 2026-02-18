package br.com.joaolirarosas.coupon_outforce.exception.handler;

import java.time.Instant;

public record ApiExceptionResponse(
        String error,
        Instant timestemp
) {
}
