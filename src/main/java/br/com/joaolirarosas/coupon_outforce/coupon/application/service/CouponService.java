package br.com.joaolirarosas.coupon_outforce.coupon.application.service;

import br.com.joaolirarosas.coupon_outforce.coupon.application.api.request.CouponRequest;
import br.com.joaolirarosas.coupon_outforce.coupon.application.api.response.CouponResponse;

import java.util.UUID;

public interface CouponService {
    CouponResponse createCoupon(CouponRequest couponRequest);

    CouponResponse detailCoupon(UUID id);

    void deleteCoupon(UUID id);
}
