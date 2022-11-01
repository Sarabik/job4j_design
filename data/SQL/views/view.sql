CREATE TABLE students (
    id serial primary key,
    name varchar(50)
);

INSERT INTO students (name)
VALUES ('Елена Осипова'), ('Игорь Ларин'), ('Леонид Норков');

CREATE TABLE authors (
    id serial primary key,
    name varchar(50)
);

INSERT INTO authors (name)
VALUES ('Александр Пушкин'), ('Николай Гоголь'), ('Александр Островский');

CREATE TABLE books (
    id serial primary key,
    name varchar(200),
    author_id integer references authors(id)
);

INSERT INTO books (name, author_id)
VALUES  ('Евгений Онегин', 1),
        ('Капитанская дочка', 1),
        ('Дубровский', 1),
        ('Мертвые души', 2),
        ('Вий', 2),
        ('Гроза', 3),
        ('Бесприданница', 3);

CREATE TABLE orders (
    id serial primary key,
    active boolean default true,
    book_id integer references books(id),
    student_id integer references students(id)
);

INSERT INTO orders (student_id, book_id)
VALUES  (1, 1), (1, 6), (1, 7),
        (2, 2), (2, 6), (2, 4), (2, 5),
        (3, 2), (3, 3);

CREATE VIEW student_with_biggest_count_of_books
AS SELECT students.name AS Student, authors.name AS Author, books.name AS Book
FROM orders
	JOIN students ON students.id = orders.student_id
	JOIN books ON books.id = orders.book_id
	JOIN authors ON authors.id = books.author_id
WHERE student_id = (SELECT student_id
					FROM orders
					GROUP BY student_id
					ORDER BY COUNT(student_id) DESC
					LIMIT 1)
ORDER BY authors.name, books.name;

