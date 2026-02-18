package br.com.joaolirarosas.coupon_outforce.coupon.application.api;

import br.com.joaolirarosas.coupon_outforce.coupon.application.api.request.CouponRequest;
import br.com.joaolirarosas.coupon_outforce.coupon.application.api.response.CouponResponse;
import br.com.joaolirarosas.coupon_outforce.coupon.application.service.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CouponController implements CouponApi {

    private final CouponService couponService;

    @Override
    public CouponResponse createCoupon(CouponRequest couponRequest) {
        log.info("[start] CouponController - createCoupon");
        CouponResponse couponCreated = couponService.createCoupon(couponRequest);
        log.debug("[finish] CouponController - createCoupon");
        return couponCreated;
    }

    @Override
    public CouponResponse detailCoupon(UUID id) {
        log.info("[start] CouponController - detailCoupon");
        CouponResponse detailedCoupon = couponService.detailCoupon(id);
        log.debug("[finish] CouponController - detailCoupon");
        return detailedCoupon;
    }

    @Override
    public void deleteCoupon(UUID id) {
        log.info("[start] CouponController - deleteCoupon");
        couponService.deleteCoupon(id);
        log.debug("[finish] CouponController - deleteCoupon");
    }
}
