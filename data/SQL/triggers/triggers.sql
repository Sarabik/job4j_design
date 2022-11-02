CREATE TABLE products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

/* Добавление налога в 30% - FOR EACH STATEMENT */
CREATE OR REPLACE FUNCTION add_tax()
RETURNS TRIGGER AS
$$
	BEGIN
		UPDATE products
		SET price = price + price * 0.3
		WHERE id = (SELECT id FROM ins_table);
		RETURN NEW;
	END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER tax_statement
AFTER INSERT ON products
REFERENCING NEW TABLE AS ins_table
FOR EACH STATEMENT
EXECUTE PROCEDURE add_tax();

INSERT INTO products (name, producer, count, price)
VALUES ('prod1', '111', 8, 90);

INSERT INTO products (name, producer, count, price)
VALUES ('prod2', '222', 12, 120);

ALTER TABLE products DISABLE TRIGGER tax_statement;

/* Добавление налога в 30% - FOR EACH ROW */
CREATE OR REPLACE FUNCTION add_tax_row()
RETURNS TRIGGER AS
$$
	BEGIN
		NEW.price = NEW.price + NEW.price * 0.3;
		RETURN NEW;
	END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER tax_row
BEFORE INSERT ON products
FOR EACH ROW
EXECUTE PROCEDURE add_tax_row();

INSERT INTO products (name, producer, count, price)
VALUES ('prod1', '111', 8, 90);

INSERT INTO products (name, producer, count, price)
VALUES ('prod2', '222', 12, 120);

ALTER TABLE products DISABLE TRIGGER tax_row;

/* Добавление информации в history_of_price через триггер */
CREATE TABLE history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

CREATE OR REPLACE FUNCTION add_history()
RETURNS TRIGGER AS
$$
	BEGIN
		INSERT INTO history_of_price(name, price, date)
		VALUES (NEW.name, NEW.price, NOW());
		RETURN NEW;
	END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER history
BEFORE INSERT ON products
FOR EACH ROW
EXECUTE PROCEDURE add_history();