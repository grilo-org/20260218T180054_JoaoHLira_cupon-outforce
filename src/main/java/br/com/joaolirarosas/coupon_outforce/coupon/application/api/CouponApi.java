package br.com.joaolirarosas.coupon_outforce.coupon.application.api;

import br.com.joaolirarosas.coupon_outforce.coupon.application.api.request.CouponRequest;
import br.com.joaolirarosas.coupon_outforce.coupon.application.api.response.CouponResponse;
import br.com.joaolirarosas.coupon_outforce.docs.swagger.CouponApiDocs;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/coupon")
public interface CouponApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CouponApiDocs.CreateCoupon
    CouponResponse createCoupon(@RequestBody @Valid CouponRequest couponRequest);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @CouponApiDocs.DetailCoupon
    CouponResponse detailCoupon(@PathVariable UUID id);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CouponApiDocs.DeleteCoupon
    void deleteCoupon(@PathVariable UUID id);
}
