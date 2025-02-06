INSERT INTO shelf (id, name, bookcase_id)
VALUES (1, '3', 1),
       (2, '7', 2),
       (3, '2', 2);

INSERT INTO users (id, email, username, password, role, client_id)
VALUES (1, 'qq@qq.qq', 'qwerty', '1234', 'ROLE_ADMIN', 1),
       (2, 'ww@ww.ww', 'asd', '7896','ROLE_USER', 2),
       (3, 'ee@ee.ee', 'zxc', '0001', 'ROLE_USER', 3);