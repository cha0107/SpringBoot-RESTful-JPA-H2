create table Employee(
	id bigint PRIMARY KEY,
	name varchar(255),
	age int,
    dept_id varchar(255)
);

create table Department(
	dept_id varchar(255) PRIMARY KEY,
	dept_name varchar(255)
);

