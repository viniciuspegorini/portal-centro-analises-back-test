INSERT INTO permission (description, action) values ('dash', 0);
INSERT INTO permission (description, action) values ('dash', 1);
INSERT INTO permission (description, action) values ('dash', 2);
INSERT INTO permission (description, action) values ('dash', 3);
INSERT INTO permission (description, action) values ('form', 0);
INSERT INTO permission (description, action) values ('form', 1);
INSERT INTO permission (description, action) values ('form', 2);
INSERT INTO permission (description, action) values ('form', 3);

INSERT INTO USERS (email, name, password, username, role) values ('marcelonavarro11md@gmail.com', 'Marcelo Falchi', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 'falchi', 1);
INSERT INTO USERS (email, name, password, username, role) values ('erickborges@gmail.com', 'Erick Borges', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 'erick', 1);
INSERT INTO USERS (email, name, password, username, role) values ('pegoras@gmail.com', 'Marco Pegoraro', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 'pegoraro', 1);
INSERT INTO USERS (email, name, password, username, role) values ('vini@gmail.com', 'Vini Braun', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 'vini_braun', 1);
INSERT INTO USERS (email, name, password, username, role) values ('menozzo@gmail.com', 'Guilherme Minozzi', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 'menozzo', 1);
INSERT INTO USERS (email, name, password, username, role) values ('leomoreno@gmail.com', 'Leonardo Moreno', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 'leo_moreno', 1);
INSERT INTO USERS (email, name, password, username, role) values ('pegorini@gmail.com', 'Vini Pegorini', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 'pegorini', 0);
INSERT INTO USERS (email, name, password, username, role) values ('marcotoninho@gmail.com', 'Marco Antonio', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 'toninho', 1);
INSERT INTO USERS (email, name, password, username, role) values ('fernanda@gmail.com', 'Fernanda', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 'fernanda', 1);
INSERT INTO USERS  (email, name, password, username, role) values ('cathula@gmail.com', 'Cathula', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 'cathula', 1);

insert into user_authorities (tb_user_id, authority_id) values (1,1);
insert into user_authorities (tb_user_id, authority_id) values (1,2);
insert into user_authorities (tb_user_id, authority_id) values (1,3);
insert into user_authorities (tb_user_id, authority_id) values (1,4);
insert into user_authorities (tb_user_id, authority_id) values (1,5);
insert into user_authorities (tb_user_id, authority_id) values (1,6);
insert into user_authorities (tb_user_id, authority_id) values (1,7);
insert into user_authorities (tb_user_id, authority_id) values (1,8);

insert into user_authorities (tb_user_id, authority_id) values (4,1);
insert into user_authorities (tb_user_id, authority_id) values (4,2);
insert into user_authorities (tb_user_id, authority_id) values (4,3);
insert into user_authorities (tb_user_id, authority_id) values (4,4);
insert into user_authorities (tb_user_id, authority_id) values (4,5);
insert into user_authorities (tb_user_id, authority_id) values (4,6);
insert into user_authorities (tb_user_id, authority_id) values (4,7);
insert into user_authorities (tb_user_id, authority_id) values (4,8);