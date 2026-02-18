package br.com.joaolirarosas.coupon_outforce.exception.handler;

import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponDomainError;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponDomainValidationException;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponErrorType;
import br.com.joaolirarosas.coupon_outforce.coupon.exceptions.ApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerTest {

    @InjectMocks
    RestExceptionHandler handler;

    @Test
    @DisplayName("Should return unprocessable entity when CouponDomainValidationException is thrown")
    void shouldReturnUnprocessableEntityWhenCouponDomainValidationExceptionIsThrown() {
        String exceptionMessage = "Domain Exception";
        CouponDomainError couponDomainError = new CouponDomainError(CouponErrorType.CODE, exceptionMessage);
        CouponDomainValidationException couponException = new CouponDomainValidationException(couponDomainError);

        ResponseEntity<CouponDomainError> exceptionReponse = handler.handleCouponDomainValidationException(couponException);

        assertEquals(422, exceptionReponse.getStatusCode().value());
        assertNotNull(exceptionReponse.getBody());
        assertEquals(CouponErrorType.CODE, exceptionReponse.getBody().type());
        assertEquals(exceptionMessage, exceptionReponse.getBody().message());
    }

    @Test
    @DisplayName("Should Return HttpCode Defined In ApiException")
    void shouldReturnHttpCodeDefinedInApiException() {
        String exceptionMessage = "NotFound Exception";
        int httpCode = 404;
        ApiException apiException = new ApiException(exceptionMessage, httpCode);

        ResponseEntity<ApiExceptionResponse> exceptionReponse = handler.handleApiException(apiException);

        assertEquals(httpCode, exceptionReponse.getStatusCode().value());
        assertNotNull(exceptionReponse.getBody());
        assertEquals(exceptionMessage, exceptionReponse.getBody().error());
        assertNotNull(exceptionReponse.getBody().timestemp());
    }
}
