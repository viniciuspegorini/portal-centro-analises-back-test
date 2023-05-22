INSERT INTO permission (description, action) values ('dash', 0);
INSERT INTO permission (description, action) values ('dash', 1);
INSERT INTO permission (description, action) values ('dash', 2);
INSERT INTO permission (description, action) values ('dash', 3);
INSERT INTO permission (description, action) values ('form', 0);
INSERT INTO permission (description, action) values ('form', 1);
INSERT INTO permission (description, action) values ('form', 2);
INSERT INTO permission (description, action) values ('form', 3);

INSERT INTO USERS (email, name, password, role) values ('marcelonavarro11md@gmail.com', 'Marcelo Falchi', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 1);
INSERT INTO USERS (email, name, password, role) values ('erickborges100@gmail.com', 'Erick Borges', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 1);
INSERT INTO USERS (email, name, password, role) values ('pegoras@gmail.com', 'Marco Pegoraro', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 1);
INSERT INTO USERS (email, name, password, role) values ('vini@gmail.com', 'Vini Braun', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 1);
INSERT INTO USERS (email, name, password, role) values ('menozzo@gmail.com', 'Guilherme Minozzi', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 1);
INSERT INTO USERS (email, name, password, role) values ('leomoreno@gmail.com', 'Leonardo Moreno', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 1);
INSERT INTO USERS (email, name, password, role) values ('pegorini@gmail.com', 'Vini Pegorini', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 0);
INSERT INTO USERS (email, name, password, role) values ('marcotoninho@gmail.com', 'Marco Antonio', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 1);
INSERT INTO USERS (email, name, password, role) values ('fernanda@gmail.com', 'Fernanda', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 1);
INSERT INTO USERS (email, name, password, role) values ('cathula@gmail.com', 'Cathula', '$2a$10$ze2T2cQAxRjql2kXEwzSZux4UMTlt/4bP.Ma/oTdekyNOqpWAUB9C', 1);

-- Carga inicial equipamentos
INSERT INTO equipment (name, value_hour_utfpr, value_hour_partner, value_sample_pf_pj) VALUES ('HPLC', 11.50, 21.00, 115.00);
INSERT INTO equipment (name, value_hour_utfpr, value_hour_partner, value_sample_pf_pj) VALUES ('CG-EM', 15.00, 25.00, 150.00);
INSERT INTO equipment (name, value_hour_utfpr, value_hour_partner, value_sample_pf_pj) VALUES ('TGA', 14.00, 22.00, 80.00);
INSERT INTO equipment (name, value_sample_utfpr, value_sample_partner, value_sample_pf_pj) VALUES ('Calorímetro diferencial exploratório (DSC)', 30.00, 45.00, 110.00);
INSERT INTO equipment (name, value_hour_utfpr, value_hour_partner, value_sample_pf_pj) VALUES ('Microscópio Eletrônico de Varredura (MEV)', 17.00, 25.00, 80.00);
INSERT INTO equipment (name, value_hour_utfpr, value_hour_partner, value_sample_pf_pj) VALUES ('DRX', 25.00, 45.00, 105.00);
INSERT INTO equipment (name, value_hour_utfpr, value_hour_partner, value_sample_pf_pj) VALUES ('Espectrômetro de Infravermelho na região do próximo com Transformada de Fourier (FT-NIR)', 2.50, 5.00, 15.00);
INSERT INTO equipment (name, value_hour_utfpr, value_hour_partner, value_sample_pf_pj) VALUES ('Espectrômetro de Infravermelho na região do Médio com Transformada de Fourier (FT-MIR)', 5.00, 15.00, 130.00);
INSERT INTO equipment (name, value_hour_utfpr, value_hour_partner, value_sample_pf_pj) VALUES ('Espectrofotômetro de Absorção Molecular (UV-Vis)', 8.00, 15.00, 70.00);
INSERT INTO equipment (name, value_hour_utfpr, value_hour_partner, value_sample_pf_pj) VALUES ('Espectrômetro de Absorção Atômica', 18.00, 30.00, 80.00);
INSERT INTO equipment (name, value_sample_utfpr, value_sample_partner, value_sample_pf_pj) VALUES ('A ELEMENTAR', 40.00, 60.00, 140.00);

INSERT INTO project (description, subject) VALUES ('PROJECT DESCRIPTION', 'PROJECT SUBJECT');

INSERT INTO partner (name) VALUES ('FADEP');
INSERT INTO partner (name) VALUES ('UNIMATER');
