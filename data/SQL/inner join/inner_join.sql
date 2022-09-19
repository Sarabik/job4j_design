create table PostOffice (
	id serial primary key,
	POnumber int,
	POindex int
);
create table Address (
	id serial primary key,
	address text,
	PostOffice_id int references PostOffice(id)
);

insert into PostOffice(POnumber, POindex) values (57, 125057);
insert into PostOffice(POnumber, POindex) values (430, 125430);
insert into PostOffice(POnumber, POindex) values (490, 125490);
insert into PostOffice(POnumber, POindex) values (47, 125047);

insert into Address(address, PostOffice_id)
values ('ул. Ленинградский проспект, дом 67, корп. 1', 1);

insert into Address(address, PostOffice_id)
values ('ул. Митинская, дом 28, корп. 3', 2);

insert into Address(address, PostOffice_id)
values ('ул. Пестеля, дом 11', 3);

insert into Address(address, PostOffice_id)
values ('ул. 1-я Тверская-Ямская, дом 29', 4);

select a.address as Адрес,
p.POnumber as "Номер почтового отделения",
p.POindex as Индекс
from Address as a
join PostOffice as p
on a.PostOffice_id = p.id
where a.address like '%ин%';

select p.POindex, a.address
from Address as a
join PostOffice as p
on a.PostOffice_id = p.id
order by p.POindex asc;

select p.POindex, a.address
from PostOffice as p
join Address as a
on a.PostOffice_id = p.id
where p.POindex = 125057;