CREATE TABLE type (
	id serial PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE product (
	id serial PRIMARY KEY,
	name VARCHAR(255),
	type_id int REFERENCES type(id),
	expired_date date,
	price decimal(8, 2)
);

INSERT INTO type(name)
VALUES ('СЫР'), ('МОЛОКО'), ('ЙОГУРТ'), ('МОРОЖЕННОЕ');

INSERT INTO product(name, type_id, expired_date, price) 
VALUES ('Голандский сыр', 1, date '2022-11-03', 350.10),
('Сыр Гауда', 1, date '2022-08-03', 420.50),
('Молоко 2,5%', 2, date '2022-10-03', 95.00),
('Молоко 1%', 2, date '2022-09-17', 85.10),
('Вкусный йогурт', 3, date '2022-11-28', 54.60),
('Огромный йогурт', 3, date '2022-12-28', 420.50),
('Сливочное мороженное', 4, date '2022-11-06', 120.00),
('Шоколадное мороженное', 4, date '2022-10-23', 102.00);

SELECT p.name, p.expired_date, p.price
FROM product AS p
JOIN type AS t 
ON p.type_id = t.id
WHERE t.name = 'СЫР';

SELECT name, expired_date, price
FROM product
WHERE name LIKE '%мороженное%';

SELECT name, expired_date, price
FROM product
WHERE expired_date < CURRENT_DATE;

SELECT name, expired_date, price
FROM product
WHERE price = (SELECT max(price) FROM product);

SELECT t.name as type, count(p.type_id) as amount
FROM product AS p
JOIN type AS t 
ON p.type_id = t.id
GROUP by t.name;

SELECT t.name AS type, p.name, p.expired_date, p.price
FROM product AS p
JOIN type AS t 
ON p.type_id = t.id
WHERE t.name = 'СЫР' OR t.name = 'МОЛОКО';

SELECT t.name AS type, count(p.type_id) AS amount
FROM type AS t 
JOIN product AS p
ON p.type_id = t.id
GROUP by t.name
HAVING count(p.type_id) < 10;

SELECT t.name AS type, p.name, p.expired_date, p.price
FROM type AS t 
JOIN product AS p
ON p.type_id = t.id;