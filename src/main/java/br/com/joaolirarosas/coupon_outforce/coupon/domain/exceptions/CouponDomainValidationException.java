package br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions;

public class CouponDomainValidationException extends RuntimeException {

    CouponErrorType type;

    public CouponDomainValidationException(CouponDomainError error) {
        super(error.message());
        this.type = error.type();
    }

    public CouponErrorType getType() {
        return type;
    }
}

