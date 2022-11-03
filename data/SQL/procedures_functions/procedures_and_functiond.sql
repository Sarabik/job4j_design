CREATE TABLE products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

/* Delete all products where their total cost is smaller then 'total_cost' */

CREATE OR REPLACE PROCEDURE delete_cheap(total_cost integer)
LANGUAGE 'plpgsql'
AS
$$
	BEGIN
		DELETE FROM products
		WHERE price * count < total_cost;
	END
$$;

INSERT INTO products(name, producer, count, price)
VALUES 	('prod1', '1', 30, 6),
		('prod2', '2', 24, 10),
		('prod3', '3', 5, 10);

CALL delete_cheap(200);

/* Delete all products where their total cost is smaller then 'total_cost'.
Return a count of deleted products */

DELETE FROM products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

CREATE OR REPLACE FUNCTION delete_cheap_f(total_cost integer)
RETURNS integer
LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		RESULT integer;
	BEGIN
		SELECT INTO RESULT COUNT(name) FROM products
		WHERE price * count < total_cost;
		DELETE FROM products
		WHERE price * count < total_cost;
	RETURN RESULT;
	END;
$$;

INSERT INTO products(name, producer, count, price)
VALUES 	('prod1', '1', 30, 6),
		('prod2', '2', 24, 10),
		('prod3', '3', 5, 10);

SELECT delete_cheap_f(200);