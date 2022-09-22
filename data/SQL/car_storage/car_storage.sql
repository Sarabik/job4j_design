CREATE TABLE car_bodies (
	id serial PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE car_engines (
	id serial PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE car_transmissions (
	id serial PRIMARY KEY,
	name VARCHAR(255)
);

CREATE TABLE cars (
	id serial PRIMARY KEY,
	name VARCHAR(255),
	body_id int REFERENCES car_bodies(id),
	engine_id int REFERENCES car_engines(id),
	transmission_id int REFERENCES car_transmissions(id)
);

INSERT INTO car_bodies(name)
VALUES ('седан'), ('хэтчбек'), ('пикап');

INSERT INTO car_engines(name)
VALUES ('1.6'), ('1.8'), ('2.0');

INSERT INTO car_transmissions(name)
VALUES ('автоматическая'), ('механическая');

INSERT INTO cars(name, body_id, engine_id, transmission_id)
VALUES  ('Машина 1', 2, 3, 1), 
		('Машина 2', 3, null, 1),
		('Машина 3', 2, 2, 2),
		('Машина 4', null, 1, 1),
		('Машина 5', 3, 3, 2);

SELECT c.id, c.name AS Машина, b.name AS Кузов, 
e.name AS Двигатель, tr.name AS "Коробка передач"
FROM cars AS c
LEFT JOIN car_bodies AS b 
ON b.id = c.body_id
LEFT JOIN car_engines AS e 
ON e.id = c.engine_id
LEFT JOIN car_transmissions AS tr 
ON tr.id = c.transmission_id;

SELECT b.name AS "Не используемые кузовы"
FROM cars AS c
RIGHT JOIN car_bodies as b ON b.id = c.body_id
WHERE c.name is null;

SELECT e.name AS "Не используемые двигатели"
FROM cars AS c
LEFT JOIN car_engines as e ON e.id = c.engine_id
WHERE c.name is null;

SELECT tr.name AS "Не используемые коробки передач"
FROM cars AS c
LEFT JOIN car_transmissions as tr ON tr.id = c.transmission_id
WHERE c.name is null;