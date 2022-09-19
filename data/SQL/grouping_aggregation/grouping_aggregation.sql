create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values 
('Sony TV', 55000),
('Sansung TV', 40000),
('Apple Watch', 35000),
('Samsung phone', 76000),
('Toy', 1005),
('Kitchen scales', 1233);

insert into people(name) values
('Olga'),
('Alexandr'),
('Elena'),
('Pavel');

insert into devices_people(device_id, people_id) values 
(1, 1),
(1, 4),
(2, 4),
(3, 2),
(4, 1),
(4, 3),
(5, 1),
(6, 1);

select avg(price) from devices;

select p.name, avg(d.price)
from devices_people as da
join devices as d
on da.device_id = d.id
join people as p
on da.people_id = p.id
group by p.name;

select p.name, avg(d.price)
from devices_people as da
join devices as d
on da.device_id = d.id
join people as p
on da.people_id = p.id
group by p.name
having avg(d.price) > 5000;