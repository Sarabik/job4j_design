/*1*/
CREATE TABLE departments (
	id serial PRIMARY KEY,
	name varchar(255)
);

CREATE TABLE employees (
	id serial PRIMARY KEY,
	name varchar(255),
	departments_id int REFERENCES departments(id)
);

INSERT INTO departments(name) 
VALUES ('dep1'), ('dep2'), ('dep3');

INSERT INTO employees(name, departments_id) 
VALUES ('empl1', 1), ('empl2', 1), ('empl3', 3), ('empl4', 1), ('empl5', 3);

/*2*/
SELECT d.name, e.name, e.departments_id 
FROM departments AS d
LEFT JOIN employees AS e
ON e.departments_id = d.id;

SELECT d.name, e.name, e.departments_id 
FROM departments AS d
RIGHT JOIN employees AS e
ON e.departments_id = d.id;

SELECT d.name, e.name, e.departments_id 
FROM departments AS d
FULL JOIN employees AS e
ON e.departments_id = d.id;

SELECT d.name, e.name, e.departments_id 
FROM departments AS d
CROSS JOIN employees AS e;

/*3*/
SELECT d.name, e.name, e.departments_id 
FROM departments AS d
LEFT JOIN employees AS e
ON e.departments_id = d.id
WHERE e.name is null;

/*4*/
SELECT d.name, e.name, e.departments_id 
FROM departments AS d
LEFT JOIN employees AS e
ON e.departments_id = d.id;

SELECT d.name, e.name, e.departments_id 
FROM employees AS e
RIGHT JOIN departments AS d
ON e.departments_id = d.id;

/*5*/
CREATE TABLE teens (
	id serial PRIMARY KEY,
	name varchar(255),
	gender varchar(1)
);

INSERT INTO teens(name, gender) 
VALUES ('Anton', 'M'), ('Liza', 'W'), ('Oleg', 'M'), ('Ivan', 'M'), ('Maria', 'W');

SELECT m.name, w.name
FROM teens AS m
CROSS JOIN teens AS w
WHERE m.gender = 'M' AND w.gender = 'W';
