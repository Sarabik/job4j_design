create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);
insert into fauna(name, avg_age, discovery_date) values ('Superfish', 600, date '1945-01-08');
insert into fauna(name, avg_age, discovery_date) values ('Lion', 4000, null);
insert into fauna(name, avg_age, discovery_date) values ('Elephant', 13000, null);
insert into fauna(name, avg_age, discovery_date) values ('Finifenmaa fish', 500, date '2022-09-03');
insert into fauna(name, avg_age, discovery_date) values ('Puma', 3600, null);