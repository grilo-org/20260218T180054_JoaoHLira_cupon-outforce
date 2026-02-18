package br.com.joaolirarosas.coupon_outforce.coupon.exceptions;

public class CouponNotFoundException extends ApiException {

    public CouponNotFoundException() {
        super("Coupon não encontrado!", 404);
    }
}
