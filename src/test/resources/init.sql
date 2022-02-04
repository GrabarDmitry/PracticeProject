USE app_test;
SET foreign_key_checks = 0;

DELETE
FROM app_test.bookmark_announcements
WHERE bookmark_announcements.bookmark_id <> 0
   OR bookmark_announcements.announcement_id <> 0;

DELETE
FROM app_test.bookmark
WHERE id <> 0;
ALTER TABLE app.bookmark
    AUTO_INCREMENT = 1;

DELETE
FROM app_test.announcement
WHERE id <> 0;
ALTER TABLE app_test.announcement
    AUTO_INCREMENT = 1;

DELETE
FROM app_test.auto
WHERE id <> 0;
ALTER TABLE app_test.auto
    AUTO_INCREMENT = 1;

DELETE
FROM app_test.auto_engine
WHERE id <> 0;
ALTER TABLE app_test.auto_engine
    AUTO_INCREMENT = 1;

DELETE
FROM app_test.auto_model
WHERE id <> 0;
ALTER TABLE app_test.auto_model
    AUTO_INCREMENT = 1;

DELETE
FROM app_test.auto_brand
WHERE id <> 0;
ALTER TABLE app_test.auto_brand
    AUTO_INCREMENT = 1;

DELETE
FROM app_test.auto_released_year
WHERE id <> 0;
ALTER TABLE app_test.auto_released_year
    AUTO_INCREMENT = 1;

DELETE
FROM app_test.auto_transmission
WHERE id <> 0;
ALTER TABLE app_test.auto_transmission
    AUTO_INCREMENT = 1;

DELETE
FROM app_test.region
WHERE id <> 0;
ALTER TABLE app_test.region
    AUTO_INCREMENT = 1;

DELETE
FROM app_test.user_roles
WHERE user_id <> 0
   OR role_id <> 0;

DELETE
FROM app_test.role
WHERE id <> 0;
ALTER TABLE app_test.role
    AUTO_INCREMENT = 1;

DELETE
FROM app_test.wallet
WHERE id <> 0;
ALTER TABLE app_test.wallet
    AUTO_INCREMENT = 1;

DELETE
FROM app_test.user
WHERE id <> 0;
ALTER TABLE app_test.user
    AUTO_INCREMENT = 1;

INSERT INTO app_test.auto_brand(title)
VALUES ('BMW'),
       ('AUDI'),
       ('Mercedes');

INSERT INTO app_test.auto_model(title, auto_brand_id, auto_released_year_id)
VALUES ('X5', 1, 1),
       ('X3', 1, 3),
       ('100', 2, 4),
       ('Q5', 2, 2),
       ('Maybach', 3, 4),
       ('AMG G 63', 3, 6);

INSERT INTO app_test.auto_released_year(released_year)
VALUES ('2020-01-01'),
       ('2014-01-01'),
       ('2017-01-01'),
       ('2019-01-01'),
       ('2006-01-01'),
       ('2017-01-01');

INSERT INTO app_test.auto_transmission(type)
VALUES ('Automatic'),
       ('Manual');

INSERT INTO app_test.auto_engine(type)
VALUES ('Petrol'),
       ('Diesel'),
       ('Electro');

INSERT INTO app_test.auto(mileage, engine_capacity, VIM, auto_model_id,
                          auto_transmission_id, auto_engine_id)
VALUES (23423, 2000, 358736475839746, 1, 1, 3),
       (223, 1900, 358736245839746, 3, 2, 2),
       (2342345, 2200, 788736475839746, 6, 2, 1),
       (232323, 1000, 358736475329746, 2, 2, 1);

INSERT INTO app_test.user(email, name, surname, password)
VALUES ('Dzmitry@mail.ru', 'Dzmitry', 'Hrabar', '$2y$12$jGKNg4LA9j.7quCszzQPmOzF36y.aTWvqSEnpnEs5TdrMITDJ33eu'),-- password: 12345
       ('user@mail.ru', 'Alex', 'Popov', '$2y$12$EvN3U/Q95Y4gLy3DtgoN5.u2jY3eWAlI0fdWaaY6dXcamRNhCJ2HO'),-- password: password
       ('Jack@mail.ru', 'Jack', 'Ni',
        '$2y$12$EvN3U/Q95Y4gLy3DtgoN5.u2jY3eWAlI0fdWaaY6dXcamRNhCJ2HO'); -- password: password;

INSERT INTO app_test.role(title)
VALUES ('ADMIN'),
       ('MODERATOR'),
       ('USER');

INSERT INTO app_test.user_roles(user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 2),
       (2, 3),
       (3, 3);

INSERT INTO app_test.region(title)
VALUES ('Brest'),
       ('Minsk'),
       ('Grodno'),
       ('Mogilev'),
       ('Gomel'),
       ('Vitebsk'),
       ('Abroad');

INSERT INTO app_test.announcement(title, description, phone_number, price, is_active, is_moderation, rating,
                                  rating_up_price,
                                  is_exchange,
                                  customs_duty, user_id, region_id, auto_id)
VALUES ('BMW X5', 'BMW X5 description', +371231834, 5000, true, false, 0, 5, false, 0, 3, 1, 1),
       ('AUDI Q5', 'AUDI Q5 description', +3712318424, 9000, true, false, 10, 5, true, 0, 2, 3, 2),
       ('Mercedes AMG G 63', 'Mercedes AMG G 63', +3712318224, 12000, true, true, 5, 5, true, 700, 1, 7, 3),
       ('BMW X3', 'BMW X3 description', +371231834, 5000, true, false, 0, 5, false, 23, 3, 3, 4);

INSERT INTO app_test.bookmark(user_id)
VALUES (1),
       (2),
       (3);

INSERT INTO app_test.bookmark_announcements(bookmark_id, announcement_id)
VALUES (1, 2),
       (2, 3),
       (3, 1);

INSERT INTO app_test.wallet(user_id, balance)
VALUES (1, 10),
       (2, 0),
       (3, 0)