-- liquibase formatted sql

-- changeset victor:1
CREATE TABLE users
(
    id      BIGSERIAL,
    login   VARCHAR(30) NOT NULL,
    name    VARCHAR(30) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
);

-- changeset victor:2
CREATE TABLE subscriptions
(
    id          BIGSERIAL,
    title       VARCHAR(30) NOT NULL,
    description TEXT,
    tariff      VARCHAR(30),
    PRIMARY KEY (id)
);

-- changeset victor:3
CREATE TABLE subscription_data
(
    id              BIGSERIAL,
    user_id         BIGINT NOT NULL,
    subscription_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (subscription_id) REFERENCES subscriptions (id)
);

-- changeset victor:4
CREATE TABLE data_values
(
    id              BIGSERIAL,
    subscription_id BIGINT       NOT NULL,
    key             VARCHAR(255) NOT NULL,
    value           VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (subscription_id) REFERENCES subscription_data (id)
);