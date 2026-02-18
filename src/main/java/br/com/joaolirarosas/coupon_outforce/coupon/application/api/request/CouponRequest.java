package br.com.joaolirarosas.coupon_outforce.coupon.application.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CouponRequest(
        @NotBlank
        String code,
        @NotBlank
        String description,
        @NotNull
        @Positive
        BigDecimal discountValue,
        @NotNull
        LocalDateTime expirationDate,
        @NotNull
        Boolean published
) {
}
