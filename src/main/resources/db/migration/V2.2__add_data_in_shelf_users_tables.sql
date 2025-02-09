INSERT INTO shelf (id, name, bookcase_id)
VALUES (1, '3', 1),
       (2, '7', 2),
       (3, '2', 2);

INSERT INTO users (id, email, username, password, role, client_id)
VALUES (1, 'qq@qq.qq', 'seva', '$2a$10$mQrDZM6ERnuz0selUQp30..tMAlkauDhYTrDuHONjjiQiI31xThHq', 'ROLE_ADMIN', null),
       (2, 'ww@ww.ww', 'rrrr', '$2a$10$e0VEY11uK2vTrVWMCy78..t3rAArQGaaJ2mizFAT/5uhjwHcfbqFC','ROLE_USER', 2),
       (3, 'ee@ee.ee', 'qqqq', '$2a$10$e0VEY11uK2vTrVWMCy78..t3rAArQGaaJ2mizFAT/5uhjwHcfbqFC','ROLE_USER', 3)