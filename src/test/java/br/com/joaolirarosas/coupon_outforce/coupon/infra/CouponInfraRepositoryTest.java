package br.com.joaolirarosas.coupon_outforce.coupon.infra;

import br.com.joaolirarosas.coupon_outforce.coupon.application.service.CouponDataHelper;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.Coupon;
import br.com.joaolirarosas.coupon_outforce.coupon.exceptions.CouponNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CouponInfraRepositoryTest {

    @InjectMocks
    CouponInfraRepository couponInfraRepository;

    @Mock
    CouponSpringDataJPARepository couponSpringDataJPARepository;

    @Test
    @DisplayName("Should save the coupon")
    void shouldSaveTheCoupon() {
        Coupon coupon = CouponDataHelper.createCoupon();
        when(couponSpringDataJPARepository.save(coupon)).thenReturn(coupon);

        couponInfraRepository.save(coupon);

        verify(couponSpringDataJPARepository, times(1)).save(coupon);
    }

    @Test
    @DisplayName("Should find the coupon by ID")
    void shouldFindTheCouponById() {
        Coupon coupon = CouponDataHelper.createCoupon();
        UUID idCoupon = UUID.fromString("59728027-e986-4827-a925-189978d77d07");
        ReflectionTestUtils.setField(coupon, "id", idCoupon);
        when(couponSpringDataJPARepository.findCouponById(idCoupon)).thenReturn(Optional.of(coupon));

        Coupon couponFinded = couponInfraRepository.findCouponById(idCoupon);

        assertEquals(idCoupon, couponFinded.getId());
        verify(couponSpringDataJPARepository, times(1)).findCouponById(idCoupon);
    }

    @Test
    @DisplayName("Should throw CouponNotFoundException when ID don't exists")
    void shouldThrowCouponNotFoundExceptionWhenIdDontExists() {
        UUID idCoupon = UUID.fromString("59728027-e986-4827-a925-189978d77d07");
        when(couponSpringDataJPARepository.findCouponById(idCoupon)).thenThrow(new CouponNotFoundException());

        CouponNotFoundException exception =
                assertThrows(CouponNotFoundException.class, () -> couponInfraRepository.findCouponById(idCoupon));

        assertEquals(404, exception.getHttpCode());
        verify(couponSpringDataJPARepository, times(1)).findCouponById(idCoupon);
    }
}
