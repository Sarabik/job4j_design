create table address (
	id serial primary key,
	street text,
	house integer,
	if_private boolean
);
insert into address (street, house, if_private) values ('Dunina', 34, false);
update address set street = 'Minina', house = 70;
delete from address;