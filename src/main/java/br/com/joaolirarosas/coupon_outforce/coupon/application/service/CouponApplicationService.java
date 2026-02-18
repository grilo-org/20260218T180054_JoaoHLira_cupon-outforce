package br.com.joaolirarosas.coupon_outforce.coupon.application.service;

import br.com.joaolirarosas.coupon_outforce.coupon.application.api.request.CouponRequest;
import br.com.joaolirarosas.coupon_outforce.coupon.application.api.response.CouponResponse;
import br.com.joaolirarosas.coupon_outforce.coupon.application.repository.CouponRepository;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.Coupon;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class CouponApplicationService implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public CouponResponse createCoupon(CouponRequest couponRequest) {
        log.info("[start] CouponApplicationService - createCoupon");
        Coupon couponCreated = new Coupon(couponRequest.code(), couponRequest.description(),
                couponRequest.discountValue(), couponRequest.expirationDate(), couponRequest.published());
        couponRepository.save(couponCreated);
        log.debug("[finish] CouponApplicationService - createCoupon");
        return new CouponResponse(couponCreated);
    }

    @Override
    public CouponResponse detailCoupon(UUID id) {
        log.info("[start] CouponApplicationService - detailCoupon");
        Coupon couponFinded = couponRepository.findCouponById(id);
        log.debug("[finish] CouponApplicationService - detailCoupon");
        return new CouponResponse(couponFinded);
    }

    @Override
    public void deleteCoupon(UUID id) {
        log.info("[start] CouponApplicationService - deleteCoupon");
        Coupon couponFinded = couponRepository.findCouponById(id);
        couponFinded.changeToDelete();
        couponRepository.save(couponFinded);
        log.debug("[finish] CouponApplicationService - deleteCoupon");
    }
}
