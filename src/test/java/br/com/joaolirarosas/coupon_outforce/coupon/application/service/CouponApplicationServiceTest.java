package br.com.joaolirarosas.coupon_outforce.coupon.application.service;

import br.com.joaolirarosas.coupon_outforce.coupon.application.api.request.CouponRequest;
import br.com.joaolirarosas.coupon_outforce.coupon.application.api.response.CouponResponse;
import br.com.joaolirarosas.coupon_outforce.coupon.application.repository.CouponRepository;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.Coupon;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.CouponStatus;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponDomainValidationException;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponErrorType;
import br.com.joaolirarosas.coupon_outforce.coupon.exceptions.CouponNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CouponApplicationServiceTest {

    @InjectMocks
    CouponApplicationService couponApplicationService;

    @Mock
    CouponRepository couponRepository;

    @Test
    @DisplayName("Should create coupon sucessfully")
    void shouldCreateCouponSucessfully() {
        CouponRequest request = CouponDataHelper.createCouponRequestValid();
        Coupon coupon = CouponDataHelper.createCouponByRequest(request);
        when(couponRepository.save(any(Coupon.class))).thenReturn(coupon);

        CouponResponse couponCreated = couponApplicationService.createCoupon(request);

        assertEquals(coupon.getDescription(), couponCreated.description());
        verify(couponRepository, times(1)).save(any(Coupon.class));
    }

    @Test
    @DisplayName("Should throw exception error when code is invalid")
    void shouldThrowExceptionErrorWhenCodeIsInvalid() {
        CouponRequest requestInvalid = CouponDataHelper.createCouponRequestWithInvalidCode();
        CouponDomainValidationException exception = assertThrows(
                CouponDomainValidationException.class, () -> couponApplicationService.createCoupon(requestInvalid));

        assertEquals(CouponErrorType.CODE, exception.getType());
        verify(couponRepository, never()).save(any(Coupon.class));
    }

    @Test
    @DisplayName("Should throw exception error when code is blanck")
    void shouldThrowExceptionErrorWhenCodeIsBlack() {
        CouponRequest requestInvalid = CouponDataHelper.createCouponRequestWithBlanckCode();
        CouponDomainValidationException exception = assertThrows(
                CouponDomainValidationException.class, () -> couponApplicationService.createCoupon(requestInvalid));

        assertEquals(CouponErrorType.CODE, exception.getType());
        verify(couponRepository, never()).save(any(Coupon.class));
    }

    @Test
    @DisplayName("Should throw exception error when discount value is less than minimum")
    void shouldThrowExceptionErrorWhenDiscountValueIsLessThanMinimum() {
        CouponRequest requestInvalid = CouponDataHelper.createCouponRequestWithDiscountLessThanMinumum();
        CouponDomainValidationException exception = assertThrows(
                CouponDomainValidationException.class, () -> couponApplicationService.createCoupon(requestInvalid));

        assertEquals(CouponErrorType.DISCOUNT, exception.getType());
        verify(couponRepository, never()).save(any(Coupon.class));
    }

    @Test
    @DisplayName("Should throw exception error when expiration date is in the past")
    void shouldThrowExceptionErrorWhenExpirationDateIsInThePast() {
        CouponRequest requestInvalid = CouponDataHelper.createCouponRequestWithExpirationDateInThePast();
        CouponDomainValidationException exception = assertThrows(
                CouponDomainValidationException.class, () -> couponApplicationService.createCoupon(requestInvalid));

        assertEquals(CouponErrorType.EXPIRATION_DATE, exception.getType());
        verify(couponRepository, never()).save(any(Coupon.class));
    }

    @Test
    @DisplayName("Should get coupon by ID sucessfully")
    void shouldGetCouponByIdSucessfully() {
        Coupon coupon = CouponDataHelper.createCoupon();
        UUID idCoupon = UUID.fromString("59728027-e986-4827-a925-189978d77d07");
        ReflectionTestUtils.setField(coupon, "id", idCoupon);
        when(couponRepository.findCouponById(idCoupon)).thenReturn(coupon);

        CouponResponse couponCreated = couponApplicationService.detailCoupon(idCoupon);

        assertEquals(idCoupon, couponCreated.id());
        assertEquals(coupon.getDescription(), couponCreated.description());
        verify(couponRepository, times(1)).findCouponById(idCoupon);
    }

    @Test
    @DisplayName("Should throw CouponNotFoundException when ID don't exists")
    void shouldThrowCouponNotFoundExceptionWhenIdDontExists() {
        UUID idCoupon = UUID.fromString("59728027-e986-4827-a925-189978d77d07");
        when(couponRepository.findCouponById(idCoupon)).thenThrow(new CouponNotFoundException());

        CouponNotFoundException exception =
                assertThrows(CouponNotFoundException.class, () -> couponApplicationService.detailCoupon(idCoupon));

        assertEquals(404, exception.getHttpCode());
        verify(couponRepository, times(1)).findCouponById(idCoupon);
    }

    @Test
    @DisplayName("Should soft delete coupon by ID sucessfully")
    void shouldSoftDeleteCouponByIdSucessfully() {
        Coupon coupon = CouponDataHelper.createCoupon();
        UUID idCoupon = UUID.fromString("59728027-e986-4827-a925-189978d77d07");
        ReflectionTestUtils.setField(coupon, "id", idCoupon);
        when(couponRepository.findCouponById(idCoupon)).thenReturn(coupon);
        when(couponRepository.save(coupon)).thenReturn(coupon);

        couponApplicationService.deleteCoupon(idCoupon);

        assertEquals(idCoupon, coupon.getId());
        assertNotNull(coupon.getDeletedAt());
        assertEquals(CouponStatus.DELETED, coupon.getStatus());
        verify(couponRepository, times(1)).findCouponById(idCoupon);
        verify(couponRepository, times(1)).save(coupon);
    }

    @Test
    @DisplayName("Should throw exception when Coupon has Already been deleted")
    void shouldThrowExceptionWhenCouponHasAlreadyBeenDeleted() {
        Coupon coupon = CouponDataHelper.createCouponDeleted();
        UUID idCoupon = UUID.fromString("59728027-e986-4827-a925-189978d77d07");
        ReflectionTestUtils.setField(coupon, "id", idCoupon);
        when(couponRepository.findCouponById(idCoupon)).thenReturn(coupon);

        CouponDomainValidationException exception =
                assertThrows(CouponDomainValidationException.class, () -> couponApplicationService.deleteCoupon(idCoupon));

        assertEquals(CouponErrorType.COUPON_STATUS, exception.getType());
        assertEquals(CouponStatus.DELETED, coupon.getStatus());
        verify(couponRepository, times(1)).findCouponById(idCoupon);
        verify(couponRepository, never()).save(coupon);
    }
}
