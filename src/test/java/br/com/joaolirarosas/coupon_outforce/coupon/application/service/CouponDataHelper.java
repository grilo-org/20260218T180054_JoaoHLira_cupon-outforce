package br.com.joaolirarosas.coupon_outforce.coupon.application.service;

import br.com.joaolirarosas.coupon_outforce.coupon.application.api.request.CouponRequest;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.Coupon;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CouponDataHelper {


    public static Coupon createCoupon() {
        CouponRequest request = createCouponRequestValid();
        return new Coupon(request.code(), request.description(), request.discountValue(), request.expirationDate(), request.published());
    }

    public static Coupon createCouponDeleted() {
        Coupon coupon = createCoupon();
        coupon.changeToDelete();
        return coupon;
    }

    public static Coupon createCouponByRequest(CouponRequest request) {
        return new Coupon(request.code(), request.description(), request.discountValue(), request.expirationDate(), request.published());
    }

    public static CouponRequest createCouponRequestValid() {
        return new CouponRequest("123456", "Coupon de teste",
                new BigDecimal("0.5"), LocalDateTime.now().plusDays(2), true);
    }

    public static CouponRequest createCouponRequestWithInvalidCode() {
        return new CouponRequest("12345#", "Coupon de teste",
                new BigDecimal("0.5"), LocalDateTime.now().plusDays(2), true);
    }

    public static CouponRequest createCouponRequestWithBlanckCode() {
        return new CouponRequest("", "Coupon de teste",
                new BigDecimal("0.5"), LocalDateTime.now().plusDays(2), true);
    }

    public static CouponRequest createCouponRequestWithDiscountLessThanMinumum() {
        return new CouponRequest("123456", "Coupon de teste",
                new BigDecimal("0.4"), LocalDateTime.now().plusDays(2), true);
    }

    public static CouponRequest createCouponRequestWithExpirationDateInThePast() {
        return new CouponRequest("123456", "Coupon de teste",
                new BigDecimal("0.5"), LocalDateTime.now().minusDays(2), true);
    }
}
