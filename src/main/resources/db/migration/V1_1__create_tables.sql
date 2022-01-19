CREATE TABLE auto_brand
(
    id    BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    title VARCHAR(45)
);

CREATE TABLE auto_model
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    title         VARCHAR(45),
    auto_brand_id BIGINT,
    CONSTRAINT auto_model_auto_brand_id_fk FOREIGN KEY (auto_brand_id) REFERENCES auto_brand (id)
);

CREATE TABLE auto_released_year
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    year          DATETIME,
    auto_model_id BIGINT,
    CONSTRAINT auto_released_year_auto_model_id_fk FOREIGN KEY (auto_model_id) REFERENCES auto_model (id)
);

CREATE TABLE auto_transmission
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    type VARCHAR(45)
);

CREATE TABLE auto_engine
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    type VARCHAR(45)
);

CREATE TABLE auto
(
    id                    BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    mileage               INT,
    engine_capacity       INT,
    VIM                   VARCHAR(17),
    auto_brand_id         BIGINT,
    auto_model_id         BIGINT,
    auto_released_year_id BIGINT,
    auto_transmission_id  BIGINT,
    auto_engine_id        BIGINT,
    CONSTRAINT auto_auto_brand_id_fk FOREIGN KEY (auto_brand_id) REFERENCES auto_brand (id),
    CONSTRAINT auto_auto_model_id_fk FOREIGN KEY (auto_model_id) REFERENCES auto_model (id),
    CONSTRAINT auto_auto_released_year_id_fk FOREIGN KEY (auto_released_year_id) REFERENCES auto_released_year (id),
    CONSTRAINT auto_transmission_id_fk FOREIGN KEY (auto_transmission_id) REFERENCES auto_transmission (id),
    CONSTRAINT auto_engine_id_fk FOREIGN KEY (auto_engine_id) REFERENCES auto_engine (id)
);

CREATE TABLE region
(
    id    BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    title VARCHAR(45)
);

CREATE TABLE `user`
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    email    VARCHAR(45),
    name     VARCHAR(45),
    surname  VARCHAR(45),
    password VARCHAR(512),
    balance  DECIMAL
);

CREATE TABLE role
(
    id    BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    title VARCHAR(45)
);

CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    CONSTRAINT user_roles_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT user_roles_role_id_fk FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE announcement
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    title          VARCHAR(90),
    description    VARCHAR(1024),
    phone_number   VARCHAR(13),
    price          DECIMAL,
    active         TINYINT,
    moderation     TINYINT,
    rating         INT,
    last_rating_up DATETIME,
    exchange       TINYINT,
    customs_duty   DECIMAL,
    user_id        BIGINT,
    region_id      BIGINT,
    auto_id        BIGINT UNIQUE,
    CONSTRAINT announcement_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT announcement_region_id_fk FOREIGN KEY (region_id) REFERENCES region (id),
    CONSTRAINT announcement_auto_id_fk FOREIGN KEY (auto_id) REFERENCES auto (id)
);

CREATE TABLE comment
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    text            VARCHAR(512),
    user_id         BIGINT,
    announcement_id BIGINT,
    CONSTRAINT comment_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT comment_announcement_id_fk FOREIGN KEY (announcement_id) REFERENCES announcement (id)
);

CREATE TABLE bookmark
(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    user_id BIGINT UNIQUE,
    CONSTRAINT bookmark_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE bookmark_announcements
(
    bookmark_id     BIGINT NOT NULL,
    announcement_id BIGINT NOT NULL,
    CONSTRAINT bookmark_announcements_bookmark_id_fk FOREIGN KEY (bookmark_id) REFERENCES bookmark (id),
    CONSTRAINT bookmark_announcements_announcement_id_fk FOREIGN KEY (announcement_id) REFERENCES announcement (id)
);
