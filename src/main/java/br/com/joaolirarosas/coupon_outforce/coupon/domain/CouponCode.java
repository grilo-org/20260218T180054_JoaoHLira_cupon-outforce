package br.com.joaolirarosas.coupon_outforce.coupon.domain;

import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponDomainError;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponDomainValidationException;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponErrorType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponCode {

    @Column(name = "code", length = 6, nullable = false)
    private String code;

    private static final Integer CODE_SIZE = 6;

    public CouponCode(String code) {
        String codeFormated = removeSpecialCharacters(code);
        validateCode(codeFormated);
        this.code = codeFormated;
    }

    private void validateCode(String code) {
        checkIfCodeIsBlanck(code);
        validateCodeSize(code);
    }

    private void checkIfCodeIsBlanck(String code) {
        if (code.isBlank()) {
            throw new CouponDomainValidationException(new CouponDomainError(CouponErrorType.CODE, "O código não deve estar em branco ou nulo!"));
        }
    }

    private void validateCodeSize(String code) {
        if (code.length() != CODE_SIZE) {
            throw new CouponDomainValidationException(new CouponDomainError(CouponErrorType.CODE, "O código deve ser alfanumérico e ter o seu tamanho igual a " + CODE_SIZE));
        }
    }

    private String removeSpecialCharacters(String code) {
        return code.replaceAll("[^a-zA-Z0-9]", "");
    }
}
