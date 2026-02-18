package br.com.joaolirarosas.coupon_outforce.coupon.domain;

import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponDomainValidationException;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponErrorType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CouponCodeTest {

    @Test
    @DisplayName("Should create coupon code sucessfully")
    void shouldCreateCouponCodeSucessfully() {
        String codeWithEspecialCharacter = "#COUPON";
        String couponCodeWithoutSpecialCharacter = "COUPON";
        CouponCode couponCode = new CouponCode(codeWithEspecialCharacter);
        assertEquals(couponCodeWithoutSpecialCharacter, couponCode.getCode());
        assertNotNull(couponCode);
    }

    @Test
    @DisplayName("Should throw exception when code size is invalid")
    void shouldThrowExceptionWhenCodeSizeIsInvalid() {
        String codeWithInvalidSize = "12345";
        CouponDomainValidationException exception =
                assertThrows(CouponDomainValidationException.class, () -> new CouponCode(codeWithInvalidSize));
        assertEquals(CouponErrorType.CODE, exception.getType());
    }

    @Test
    @DisplayName("Should throw exception when code is blanck")
    void shouldThrowExceptionWhenCodeIsBlanck() {
        String blanckCode = "";
        CouponDomainValidationException exception =
                assertThrows(CouponDomainValidationException.class, () -> new CouponCode(blanckCode));
        assertEquals(CouponErrorType.CODE, exception.getType());
    }

}