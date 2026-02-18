package br.com.joaolirarosas.coupon_outforce.coupon.infra;

import br.com.joaolirarosas.coupon_outforce.coupon.application.repository.CouponRepository;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.Coupon;
import br.com.joaolirarosas.coupon_outforce.coupon.exceptions.CouponNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Log4j2
@Repository
@RequiredArgsConstructor
public class CouponInfraRepository implements CouponRepository {

    private final CouponSpringDataJPARepository couponSpringDataJPARepository;

    @Override
    public Coupon save(Coupon coupon) {
        log.info("[start] CouponInfraRepository - save");
        couponSpringDataJPARepository.save(coupon);
        log.debug("[finish] CouponInfraRepository - save");
        return coupon;
    }

    @Override
    public Coupon findCouponById(UUID id) {
        log.info("[start] CouponInfraRepository - findCouponById");
        Coupon couponFinded = couponSpringDataJPARepository.findCouponById(id)
                .orElseThrow(CouponNotFoundException::new);
        log.debug("[finish] CouponInfraRepository - findCouponById");
        return couponFinded;
    }
}