INSERT INTO shelf (id, name, bookcase_id)
VALUES (1, '1A', 1),
       (2, '2А', 2),
       (3, '2Б', 2),
       (4, '3А', 3),
       (5, '3Б', 3),
       (6, '3В', 3),
       (7, '3Г', 3),
       (8, '3Д', 3),
       (9, '3Е', 3),
       (10, '1Б', 1),
       (11, '1В', 1),
       (12, '1Г', 1),
       (13, '1Д', 1),
       (14, '1Е', 1),
       (15, '1Ж', 1);

INSERT INTO users (id, email, username, password, role, client_id)
VALUES (1, 'qq@qq.qq', 'seva', '$2a$10$mQrDZM6ERnuz0selUQp30..tMAlkauDhYTrDuHONjjiQiI31xThHq', 'ROLE_ADMIN', null),
       (2, 'ww@ww.ww', 'rrrr', '$2a$10$e0VEY11uK2vTrVWMCy78..t3rAArQGaaJ2mizFAT/5uhjwHcfbqFC','ROLE_USER', 2),
       (3, 'ee@ee.ee', 'qqqq', '$2a$10$e0VEY11uK2vTrVWMCy78..t3rAArQGaaJ2mizFAT/5uhjwHcfbqFC','ROLE_USER', 3)