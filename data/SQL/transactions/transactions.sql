create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

BEGIN TRANSACTION;
SAVEPOINT point1;
INSERT INTO products (name, producer, count, price)
VALUES ('prod1', 'producer1', 73, 80);
SELECT * FROM products;
SAVEPOINT point2;
INSERT INTO products (name, producer, count, price)
VALUES ('prod2', 'producer2', 30, 20);
UPDATE products
SET price = 60 WHERE count > 70;
SELECT * FROM products;
SAVEPOINT point3;
ROLLBACK TO SAVEPOINT point2;
SELECT * FROM products;
ROLLBACK TO SAVEPOINT point1;
COMMIT;
SELECT * FROM products;