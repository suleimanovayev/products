INSERT into products (id, name, parent_id, price)
values (1, 'Tablets', 0, 0);
INSERT into products (id, name, parent_id, price)
values (2, 'Phones', 0, '0');
INSERT into products (id, name, parent_id, price)
values (3, 'Laptops', 0, 0);
INSERT into products (id, name, parent_id, price)
values (4, 'Monitors', 0, 0);

INSERT into products (id, name, parent_id, price)
values (5, 'Huawei MatePad T8 LTE 16GB Deepsea Blue', 1, 3300);
INSERT into products (id, name, parent_id, price)
values (6, 'Lenovo Tab M7 32GB LTE Iron Grey', 1, 3500);
INSERT into products (id, name, parent_id, price)
values (7, 'Pixus Touch 7 3G (qHD) 2/16GB', 1, 2800);
INSERT into products (id, name, parent_id, price)
values (8, 'MiXzo MX 1021 3G 10.1" 1/16GB', 1, 5000);

INSERT into products (id, name, parent_id, price)
values (9, 'Smartphones', 2, 0);
INSERT into products (id, name, parent_id, price)
values (10, 'Office phones', 2, 0);
INSERT into products (id, name, parent_id, price)
values (11, 'Xiaomi Redmi 9C ', 9, 3500);
INSERT into products (id, name, parent_id, price)
values (12, 'Apple iPhone 12 Pro Max', 9, 40449);
INSERT into products (id, name, parent_id, price)
values (13, 'Apple iPhone 12 Pro Max', 9, 1450);

INSERT into products (id, name, parent_id, price)
values (14, 'Panasonic KX-TG1611UAH Grey', 10, 814);
INSERT into products (id, name, parent_id, price)
values (15, 'Alcatel T06 White (3700601415599)', 10, 237);

INSERT into products (id, name, parent_id, price)
values (16, 'Asus X509UB-BQ084 (90NB0ND2-M01650) Slate Grey', 3, 15555);
INSERT into products (id, name, parent_id, price)
values (17, 'HP 15-db1096ur (7RZ13EA) Black', 3, 16899);
INSERT into products (id, name, parent_id, price)
values (18, 'HP Pavilion 15-cw1001ua (7KD45EA) Silver', 3, 19999);
INSERT into products (id, name, parent_id, price)
values (19, 'Acer Aspire 5 A515-55G (NX.HZFEU.009) Pure Silver', 3, 18999);
INSERT into products (id, name, parent_id, price)
values (20, 'XIAOMI Mi Display 34', 4, 13999);
INSERT into products (id, name, parent_id, price)
values (21, 'Samsung S22F350F (LS22F350FHIXCI) 28', 4, 2444);
INSERT into products (id, name, parent_id, price)
values (22, 'MSI Optix MPG341CQR USB Type C 38', 4, 26999);
INSERT into products (id, name, parent_id, price)
values (23, 'Dell U4919DW (210-ARGK) 49', 4, 23566);

INSERT into users (id, first_name, last_name, email, password)
values (1, 'Anna', 'Kross', 'annakros@gmail.com', '$2a$10$rObsFheOgaroqFWQEB6etuK9v3aAEdVHpzAgMgJFx/eFR0pfAdtMu');
INSERT into users (id, first_name, last_name, email, password)
values (2, 'Kevin', 'De Bruyne', 'kevindebruyne@gmail.com', '$2a$10$YcDq3n4GOZMiqo3Pb8JmxevTL/l/mrDcaiOEjnuuqhC2ucspCWHkG');


INSERT into roles (id, role_name)
values (1, 'ADMIN');
INSERT into roles (id, role_name)
values (2, 'USER');

INSERT into user_role (user_id, role_id)
values (1, 1);
INSERT into user_role (user_id, role_id)
values (2, 2);