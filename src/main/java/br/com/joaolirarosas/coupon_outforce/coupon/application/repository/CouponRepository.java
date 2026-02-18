package br.com.joaolirarosas.coupon_outforce.coupon.application.repository;

import br.com.joaolirarosas.coupon_outforce.coupon.domain.Coupon;

import java.util.UUID;

public interface CouponRepository {
    Coupon save(Coupon couponCreated);

    Coupon findCouponById(UUID id);
}
