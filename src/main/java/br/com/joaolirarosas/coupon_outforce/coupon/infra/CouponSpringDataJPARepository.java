package br.com.joaolirarosas.coupon_outforce.coupon.infra;

import br.com.joaolirarosas.coupon_outforce.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CouponSpringDataJPARepository extends JpaRepository<Coupon, UUID> {
    Optional<Coupon> findCouponById(UUID id);
}
