package br.com.joaolirarosas.coupon_outforce.coupon.domain;

import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponDomainError;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponDomainValidationException;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponErrorType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponDiscount {

    @Column(name = "discount_value", precision = 10, scale = 2, nullable = false)
    private BigDecimal value;

    public CouponDiscount(BigDecimal value) {
        checkIfIsLessThanMinimumDiscount(value);
        this.value = value;
    }

    private static final BigDecimal MINIMUM_DISCOUNT = new BigDecimal("0.5");

    private void checkIfIsLessThanMinimumDiscount(BigDecimal value) {
        if (value.compareTo(MINIMUM_DISCOUNT) < 0) {
            throw new CouponDomainValidationException(
                    new CouponDomainError(CouponErrorType.DISCOUNT, "O valor minimo de desconto é: " + MINIMUM_DISCOUNT));
        }
    }
}
