CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, name)
VALUES (1, 'c1'), (2, 'c2'), (3, 'c3'), (4, 'c4'), (5, 'c5');

INSERT INTO person (id, name, company_id)
VALUES  (1, 'name1', 3),
        (2, 'name2', 3),
        (3, 'name3', 5),
        (4, 'name4', 5),
        (5, 'name5', 1);

/* First task */
select p.name, c.name
from person as p
join company as c
on p.company_id = c.id
where p.company_id != 5
order by p.name;

/* Second task */
select company.name, max_people.people_count
from
	(select company_id, count(company_id) as people_count
	from person
	group by company_id
	having count(company_id) =
		(select count(company_id) as people_count
		from person
		group by company_id
		order by people_count desc
		limit 1))
		as max_people
join company
on company.id = max_people.company_id
order by company.name;