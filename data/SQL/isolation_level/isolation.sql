/* Read Committed */
CREATE TABLE books (
id serial primary key,
book_name varchar(100),
book_author varchar(50),
count integer default 0
);

INSERT INTO books (book_name, book_author, count)
VALUES ('book1', 'author1', 15),
('book2', 'author2', 20);

/* First parallel transaction */
BEGIN TRANSACTION;
INSERT INTO books (book_name, book_author, count)
VALUES ('book3', 'author3', 35);
DELETE FROM books WHERE count = 15;
SELECT * FROM books;
COMMIT;
SELECT * FROM books;

/* Second parallel transaction */
BEGIN TRANSACTION;
SELECT * FROM books;
SELECT * FROM books;
COMMIT;



/* Repeatable Read */
CREATE TABLE books2 (
id serial primary key,
book_name varchar(100),
book_author varchar(50),
count integer default 0
);

INSERT INTO books2 (book_name, book_author, count)
VALUES ('book1', 'author1', 15),
('book2', 'author2', 20);

/* First parallel transaction */
BEGIN TRANSACTION isolation level repeatable read;
INSERT INTO books2 (book_name, book_author, count)
VALUES ('book3', 'author3', 35);
UPDATE books2 SET count = 50 WHERE book_name = 'book1';
ROLLBACK;

/* Second parallel transaction */
BEGIN TRANSACTION isolation level repeatable read;
UPDATE books2 SET count = 100 WHERE book_name = 'book1';
COMMIT;


/* Serializable */
CREATE TABLE books3 (
id serial primary key,
book_name varchar(100),
book_author varchar(50),
count integer default 0
);

INSERT INTO books3 (book_name, book_author, count)
VALUES ('book1', 'author1', 15),
('book2', 'author2', 20);

/* First parallel transaction */
BEGIN TRANSACTION isolation level serializable;
SELECT MAX(count) FROM books3;
UPDATE books3 SET count = 50 WHERE book_name = 'book1';
COMMIT;

/* Second parallel transaction */
BEGIN TRANSACTION isolation level serializable;
SELECT MAX(count) FROM books3;
UPDATE books3 SET count = 10 WHERE book_name = 'book2';
COMMIT;