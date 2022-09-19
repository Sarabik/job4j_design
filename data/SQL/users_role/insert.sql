insert into role(name) values ('admin');
insert into role(name) values ('user');
insert into users(name, role_id) values ('Anton', 1);
insert into users(name, role_id) values ('Olga', 2);
insert into rules(name) values ('Rule1');
insert into category(name) values ('Category1');
insert into state(name) values ('State1');
insert into item(name, users_id, category_id, state_id) values ('NewItem', 1, 1, 1);
insert into comments(name, item_id) values ('Comment1', 1);
insert into attachs(name, item_id) values ('Attach1', 1);


