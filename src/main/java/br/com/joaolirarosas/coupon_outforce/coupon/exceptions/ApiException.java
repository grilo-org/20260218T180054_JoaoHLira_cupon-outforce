package br.com.joaolirarosas.coupon_outforce.coupon.exceptions;

public class ApiException extends RuntimeException{

    Integer httpCode;

    public ApiException(String message, Integer httpCode) {
        super(message);
        this.httpCode = httpCode;
    }

    public Integer getHttpCode() {
        return httpCode;
    }
}
