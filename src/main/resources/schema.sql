CREATE SCHEMA IF NOT EXISTS mydatabase;

CREATE TABLE IF NOT EXISTS mydatabase.contacts (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    phone BIGINT NOT NULL,
    primary_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (primary_id) REFERENCES mydatabase.contacts(id)
);