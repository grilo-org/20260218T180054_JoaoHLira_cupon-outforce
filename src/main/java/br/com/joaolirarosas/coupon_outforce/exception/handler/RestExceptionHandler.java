package br.com.joaolirarosas.coupon_outforce.exception.handler;

import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponDomainError;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponDomainValidationException;
import br.com.joaolirarosas.coupon_outforce.coupon.exceptions.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(CouponDomainValidationException.class)
    public ResponseEntity<CouponDomainError> handleCouponDomainValidationException(CouponDomainValidationException exception) {
        return ResponseEntity
                .unprocessableEntity()
                .body(new CouponDomainError(exception.getType(), exception.getMessage()));
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionResponse> handleApiException(ApiException exception) {
        return ResponseEntity
                .status(exception.getHttpCode())
                .body(new ApiExceptionResponse(exception.getMessage(), Instant.now()));
    }
}

