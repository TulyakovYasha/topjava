DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO meals(user_id, datetime, description, calories)
VALUES (100000, '2019-06-24 10:00:00', 'firstUserMeal', 700),
       (100000, '2019-06-23 13:00:00', 'SecondUserMeal', 900),
       (100001, '2019-06-24 9:00:00', 'AdminMeal', 800);

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001);
