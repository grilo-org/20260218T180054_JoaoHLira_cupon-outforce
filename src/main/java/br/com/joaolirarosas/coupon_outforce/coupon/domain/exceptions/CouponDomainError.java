package br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions;

public record CouponDomainError(
        CouponErrorType type,
        String message
) {
}
