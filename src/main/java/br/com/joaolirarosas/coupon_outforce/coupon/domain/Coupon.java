package br.com.joaolirarosas.coupon_outforce.coupon.domain;

import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponDomainError;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponDomainValidationException;
import br.com.joaolirarosas.coupon_outforce.coupon.domain.exceptions.CouponErrorType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Embedded
    private CouponCode code;

    @Column(name = "description", nullable = false)
    private String description;

    @Embedded
    private CouponDiscount discount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CouponStatus status;

    @Column(name = "published", nullable = false)
    private Boolean published = false;

    @Column(name = "redeemed", nullable = false)
    private Boolean redeemed = false;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Coupon(String code, String description, BigDecimal discountValue, LocalDateTime expirationDate, Boolean published) {
        this.code = new CouponCode(code);
        this.description = description;
        this.discount = new CouponDiscount(discountValue);
        this.createdAt = LocalDateTime.now();
        checkIfExpirationDateHasPassed(expirationDate);
        this.expirationDate = expirationDate;
        this.published = published;
        this.status = determinesStatusByPublished(published);
    }

    private void checkIfExpirationDateHasPassed(LocalDateTime expirationDate) {
        if (expirationDate.isBefore(LocalDateTime.now())) {
            throw new CouponDomainValidationException(new CouponDomainError(CouponErrorType.EXPIRATION_DATE, "A data de expiração não deve estar no passado"));
        }
    }

    private CouponStatus determinesStatusByPublished(Boolean published) {
        return Boolean.TRUE.equals(published) ? CouponStatus.ACTIVE : CouponStatus.INACTIVE;
    }

    public void changeToDelete() {
        checkIfIsMarkedAsDeleted();
        this.status = CouponStatus.DELETED;
        this.deletedAt = LocalDateTime.now();
    }

    private void checkIfIsMarkedAsDeleted() {
        if (this.status.equals(CouponStatus.DELETED)) {
            throw new CouponDomainValidationException(new CouponDomainError(CouponErrorType.COUPON_STATUS, "Cupom já foi deletado!"));
        }
    }
}
