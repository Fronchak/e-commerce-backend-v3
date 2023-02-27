INSERT INTO role (authority) VALUES ('ROLE_CLIENT');
INSERT INTO role (authority) VALUES ('ROLE_WORKER');
INSERT INTO role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user (username, password) VALUES ('client@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (username, password) VALUES ('worker@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (username, password) VALUES ('admin@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO user_role (id_user, id_role) VALUES (1, 1);
INSERT INTO user_role (id_user, id_role) VALUES (2, 1);
INSERT INTO user_role (id_user, id_role) VALUES (2, 2);
INSERT INTO user_role (id_user, id_role) VALUES (3, 1);
INSERT INTO user_role (id_user, id_role) VALUES (3, 2);
INSERT INTO user_role (id_user, id_role) VALUES (3, 3);