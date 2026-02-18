package br.com.joaolirarosas.coupon_outforce.coupon.domain;

import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponDomainValidationException;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponErrorType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CouponTest {

    @Test
    @DisplayName("Should create a active coupon sucessfully")
    void shouldCreateAActiveCouponSucessfully() {
        Coupon coupon = new Coupon("COUPON", "A valid Coupon active",
                new BigDecimal("0.5"), LocalDateTime.now().plusDays(2), true);
        assertNotNull(coupon);
        assertEquals(CouponStatus.ACTIVE, coupon.getStatus());
    }

    @Test
    @DisplayName("Should create a inactive coupon sucessfully")
    void shouldCreateAInactiveCouponSucessfully() {
        Coupon coupon = new Coupon("COUPON", "A valid Coupon inactive",
                new BigDecimal("0.5"), LocalDateTime.now().plusDays(2), false);
        assertNotNull(coupon);
        assertEquals(CouponStatus.INACTIVE, coupon.getStatus());
    }

    @Test
    @DisplayName("Should throw exception when expiration date is in the past")
    void shouldThrowExceptionWhenExpirationDateIsInThePast() {
        CouponDomainValidationException exception = assertThrows(CouponDomainValidationException.class,
                () -> new Coupon("COUPON", "A valid Coupon inactive", new BigDecimal("0.5"), LocalDateTime.now().minusDays(2), true));
        assertEquals(CouponErrorType.EXPIRATION_DATE, exception.getType());
    }

    @Test
    @DisplayName("Should change the status of the coupon to deleted")
    void shouldChangeTheStatusOfCouponToDeleted() {
        Coupon coupon = new Coupon("COUPON", "A valid Coupon active",
                new BigDecimal("0.5"), LocalDateTime.now().plusDays(2), true);
        coupon.changeToDelete();
        assertEquals(CouponStatus.DELETED, coupon.getStatus());
        assertNotNull(coupon.getDeletedAt());
    }

    @Test
    @DisplayName("Should throw exception when coupon is already in the deleted status")
    void shouldThrowExceptionWhenCouponIsAlreadyInDeletedStatus() {
        Coupon coupon = new Coupon("COUPON", "A valid Coupon deleted",
                new BigDecimal("0.5"), LocalDateTime.now().plusDays(2), true);
        coupon.changeToDelete();
        CouponDomainValidationException exception = assertThrows(CouponDomainValidationException.class, coupon::changeToDelete);
        assertEquals(CouponErrorType.COUPON_STATUS, exception.getType());
    }
}