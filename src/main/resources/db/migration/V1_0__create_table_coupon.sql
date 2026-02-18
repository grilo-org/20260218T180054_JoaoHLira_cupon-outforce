CREATE TABLE coupon (
    id UUID NOT NULL,
    code VARCHAR(6),
    description VARCHAR(255) NOT NULL,
    discount_value DECIMAL(19, 2),
    created_at TIMESTAMP NOT NULL,
    expiration_date TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    status VARCHAR(50) NOT NULL,
    published BOOLEAN DEFAULT FALSE NOT NULL,
    redeemed BOOLEAN DEFAULT FALSE NOT NULL,
    PRIMARY KEY (id)
);