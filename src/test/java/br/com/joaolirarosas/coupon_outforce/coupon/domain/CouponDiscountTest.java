package br.com.joaolirarosas.coupon_outforce.coupon.domain;

import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponDomainValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CouponDiscountTest {

    @Test
    @DisplayName("Should create coupon discount sucessfully")
    void shouldCreateCouponDiscountSucessfully() {
        CouponDiscount couponDiscount = new CouponDiscount(new BigDecimal("0.5"));
        assertEquals(new BigDecimal("0.5"), couponDiscount.getValue());
        assertNotNull(couponDiscount);
    }

    @Test
    @DisplayName("Should not allow discount is less than minimum")
    void shouldNotAllowDiscountIsLessThanMinimum() {
        assertThrows(
                CouponDomainValidationException.class,
                () -> new CouponDiscount(new BigDecimal("0.1"))
        );
    }

}