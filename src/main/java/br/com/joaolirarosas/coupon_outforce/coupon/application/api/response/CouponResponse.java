package br.com.joaolirarosas.coupon_outforce.coupon.application.api.response;

import br.com.joaolirarosas.coupon_outforce.coupon.domain.Coupon;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.CouponStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CouponResponse(
        UUID id,
        String code,
        String description,
        BigDecimal discountValue,
        LocalDateTime createdAt,
        LocalDateTime expirationDate,
        LocalDateTime deletedAt,
        CouponStatus status,
        Boolean published,
        Boolean redeemed
) {

    public CouponResponse(Coupon coupon) {
        this(
                coupon.getId(),
                coupon.getCode().getCode(),
                coupon.getDescription(),
                coupon.getDiscount().getValue(),
                coupon.getCreatedAt(),
                coupon.getExpirationDate(),
                coupon.getDeletedAt(),
                coupon.getStatus(),
                coupon.getPublished(),
                coupon.getRedeemed());
    }
}