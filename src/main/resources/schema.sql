CREATE SCHEMA IF NOT EXISTS gse_cart;

CREATE TABLE IF NOT EXISTS gse_cart.contacts (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    phone BIGINT NOT NULL,
    primary_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (primary_id) REFERENCES gse_cart.contacts(id)
);