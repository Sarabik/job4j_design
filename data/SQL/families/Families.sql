create table man (
	id serial primary key,
	name varchar(255)
);
create table woman (
	id serial primary key,
	name varchar(255)
);
create table couple (
	id serial primary key,
	surname varchar(255),
	husband_id int references man(id) unique,
	wife_id int references woman(id) unique
);
create table pet_type (
	id serial primary key,
	pet_type varchar(255)
);
create table couple_pet_type (
	id serial primary key,
	couple_id int references couple(id),
	pet_type_id int references pet_type(id)
);
create table child (
	id serial primary key,
	name varchar(255),
	mother_id int references woman(id)
);